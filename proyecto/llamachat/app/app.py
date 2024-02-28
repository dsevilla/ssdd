import uuid
import http
import time
from threading import Thread, Lock
from flask import Flask, Response, redirect, request, url_for, jsonify
import os

class LLM_base:
    _llm = None
    llm_set = False

    # Prompt map
    prompt_map = {}
    # Prompt request stack
    prompt_stack = []
    prompt_map_lock = Lock()  # Will be used for both the map and the stack
    
    def init_model(self):
        pass

    def llm(self, token, prompt):
        pass

if os.environ.get('DUMMY'):
    class LLM(LLM_base):

        def init_model(self):
            time.sleep(5)
            self.llm_set = True

        def llm(self, token, prompt):
            time.sleep(5)
            return f"""DUMMY Generated response for token "{token}"."""
else:
    class LLM(LLM_base):

        def init_model(self):
            from ctransformers import AutoModelForCausalLM

            # Set gpu_layers to the number of layers to offload to GPU. Set to 0 if no GPU acceleration is available on your system.
            self._llm = AutoModelForCausalLM.from_pretrained("TheBloke/Llama-2-7b-Chat-GGUF",
                                                        model_file="llama-2-7b-chat.Q4_K_M.gguf",
                                                        model_type="llama",
                                                        gpu_layers=0)
            self.llm_set = True

        def llm(self, token, prompt):
            return self._llm(prompt)
        
        
llm = LLM()


def init_model_and_process_requests():
    global llm

    llm.init_model()

    # infinite loop
    while True:
        llm.prompt_map_lock.acquire()
        if len(llm.prompt_stack) == 0:
            llm.prompt_map_lock.release()
            time.sleep(.1)
        else:
            (token, prompt) = llm.prompt_stack.pop(0)
            llm.prompt_map_lock.release()

            # Generate a response
            prompt['answer'] = llm.llm(token, prompt['prompt'].strip())

Thread(target=init_model_and_process_requests).start()

def handle_response_request(prompt: dict) -> dict:
    # Generate UUID for request
    token = str(uuid.uuid4())
    # Lock the access to the map
    llm.prompt_map_lock.acquire()
    # Push the pending work on the stack
    llm.prompt_stack.append((token, prompt))
    # And add it incomplete to the prompt map
    llm.prompt_map[token] = prompt
    llm.prompt_map_lock.release()
    
    return token


app = Flask(__name__, static_url_path='')


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
        if not llm.llm_set:
            return Response('Still initializing...\n', http.HTTPStatus.PROCESSING)
        if not request.is_json or not request.json['prompt']:
            return Response('application/json type required, with {"prompt": "..."} format.\n',
                             http.HTTPStatus.UNSUPPORTED_MEDIA_TYPE)
        token = handle_response_request(request.json)
        return Response('Accepted\n', 
                        status=http.HTTPStatus.ACCEPTED,
                        headers={"Location": f"/response/{token}"})
    else:
        return "🦙chat v 1.0! Use POST to ask for a prompt."

@app.route('/response/<token>', methods=['GET'])
def resp(token):
    if not llm.llm_set:
        return Response('Still initializing...\n', http.HTTPStatus.NO_CONTENT)
    llm.prompt_map_lock.acquire()
    res = llm.prompt_map.get(str(token), None)
    llm.prompt_map_lock.release()
    if not res:
        return Response('Token unknown.\n', http.HTTPStatus.NOT_FOUND)
    if not res.get('answer', None):
        return Response('Response still being generated.\n',
                        http.HTTPStatus.PROCESSING)
    else:
        return jsonify(res)

@app.route('/healthcheck', methods=['GET'])
def healthcheck():
    if not llm.llm_set:
        return Response(status=http.HTTPStatus.NO_CONTENT)
    return Response(status=http.HTTPStatus.OK)

if __name__ == '__main__':
    # Start the download of the model, if needed
    Thread(target=init_model_and_process_requests).start()

    app.run(debug=True, host='0.0.0.0', port=int(os.environ.get('PORT', 5020)))
