import uuid
import http
from threading import Thread, Lock
from flask import Flask, Response, redirect, request, url_for, jsonify
from ctransformers import AutoModelForCausalLM

app = Flask(__name__, static_url_path='')

llm = None
llm_set = False
llm_lock = Lock()

# Prompt map
prompt_map = {}
prompt_map_lock = Lock()

def init_model():
    global llm
    global llm_set
    # Set gpu_layers to the number of layers to offload to GPU. Set to 0 if no GPU acceleration is available on your system.
    llm = AutoModelForCausalLM.from_pretrained("TheBloke/Llama-2-7b-Chat-GGUF",
                                               model_file="llama-2-7b-chat.Q4_K_M.gguf",
                                               model_type="llama",
                                               gpu_layers=0)
    llm_set = True

Thread(target=init_model).start()

def generate_response(prompt: dict):
    llm_lock.acquire()
    prompt['answer'] = llm(prompt['prompt'].strip())
    llm_lock.release()

def handle_response_request(prompt: dict) -> dict:
    # Generate UUID for request
    token = str(uuid.uuid4())
    # Lock the access to the map
    prompt_map_lock.acquire()
    prompt_map[token] = prompt
    prompt_map_lock.release()

    # Generate a new llm thread
    Thread(target=generate_response,args=[prompt]).start()
    
    return token

# Configurar el secret_key. OJO, no debe ir en un servidor git público.
# Python ofrece varias formas de almacenar esto de forma segura, que
# no cubriremos aquí.
app.config['SECRET_KEY'] = 'abcdefghijklmnopqrstuvwxyz0123456789'

@app.route('/')
def index():
    return redirect(url_for('prompt'))

@app.route('/prompt', methods=['GET', 'POST'])
def prompt():
    if request.method == "POST":
        if not llm_set:
            return Response('Still initializing...\n', http.HTTPStatus.PROCESSING)
        if llm_lock.locked():
            return Response('Generating previous response... Please, try again later...\n',
                            http.HTTPStatus.PROCESSING)
        if not request.is_json or not request.json['prompt']:
            return Response('application/json type required, with {"prompt": "..."} format.\n',
                             http.HTTPStatus.UNSUPPORTED_MEDIA_TYPE)
        token = handle_response_request(request.json)
        return Response('Accepted\n', status=http.HTTPStatus.ACCEPTED, headers={"Location": f"/response/{token}"})
    else:
        return "🦙chat v 1.0! Use POST to ask for a prompt."

@app.route('/response/<token>', methods=['GET'])
def resp(token):
    if not llm_set:
        return Response('Still initializing...\n', http.HTTPStatus.NO_CONTENT)
    prompt_map_lock.acquire()
    res = prompt_map.get(str(token), None)
    prompt_map_lock.release()
    if not res:
        return Response('Token unknown.\n', http.HTTPStatus.NOT_FOUND)
    if not res.get('answer',None):
        return Response('Response still being generated.\n',
                        http.HTTPStatus.PROCESSING)
    else:
        return jsonify(res)

@app.route('/healthcheck', methods=['GET'])
def healthcheck():
    if not llm_set:
        return Response(status=http.HTTPStatus.NO_CONTENT)
    return Response(status=http.HTTPStatus.OK)

if __name__ == '__main__':
    # Start the download of the model, if needed
    Thread(target=init_model).start()

    app.run(debug=True, host='0.0.0.0')
