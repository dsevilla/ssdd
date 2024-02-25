import uuid
import http
from threading import Thread, Lock
from flask import Flask, Response, redirect, request, url_for
from ctransformers import AutoModelForCausalLM

app = Flask(__name__, static_url_path='')

llm = None
llm_set = False

# Prompt map
prompt_map = {}
prompt_map_lock = Lock()

def init_model():
    # Set gpu_layers to the number of layers to offload to GPU. Set to 0 if no GPU acceleration is available on your system.
    llm = AutoModelForCausalLM.from_pretrained("TheBloke/Llama-2-7b-Chat-GGUF",
                                               model_file="llama-2-7b-chat.Q4_K_M.gguf",
                                               model_type="llama",
                                               gpu_layers=0)
    llm_set = True

def generate_response(prompt: dict) -> dict: 
    prompt['answer'] = llm(prompt['prompt'])
    return prompt

def handle_response_request(prompt: dict) -> dict:
    # Generate UUID for request
    token = uuid.uuid4
    # Lock the access to the map
    prompt_map_lock.acquire()
    prompt_map[token] = prompt
    prompt_map_lock.release()

    # Generate a new llm thread
    Thread(target=generate_response,args=[prompt]).start()

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
        if not request.is_json or not request.json['prompt']:
            return Response('application/json type required, with {"prompt": "..."} format.',
                             http.HTTPStatus.UNSUPPORTED_MEDIA_TYPE)
        handle_response_request(request.json['prompt'].strip())
    else:
        return "🦙chat v 1.0! Use POST to ask for a prompt."

@app.route('/response/<token>', methods=['GET'])
def response(token):
    prompt_map_lock.acquire()
    res = prompt_map.get(token, None)
    prompt_map_lock.release()
    if res and res.get('answer',None):
        return res['answer']
    else:
        return Response('Token unknown or response still being generated.',
                        http.HTTPStatus.NO_CONTENT)

@app.route('/healthcheck', methods=['GET'])
def healthcheck():
    if not llm_set:
        return Response(status=http.HTTPStatus.NO_CONTENT)
    return Response(status=http.HTTPStatus.OK)

if __name__ == '__main__':
    # Start the download of the model, if needed
    Thread(target=init_model).start()

    app.run(debug=True, host='0.0.0.0')
