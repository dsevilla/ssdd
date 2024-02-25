from flask import Flask
import os

app = Flask(__name__, static_url_path='')

from ctransformers import AutoModelForCausalLM

# Set gpu_layers to the number of layers to offload to GPU. Set to 0 if no GPU a
cceleration is available on your system.
llm = AutoModelForCausalLM.from_pretrained("TheBloke/Llama-2-7b-Chat-GGUF", mode
l_file="llama-2-7b-chat.Q4_K_M.gguf", model_type="llama", gpu_layers=0)

print(llm("what is the area of a circle?"))
# Configurar el secret_key. OJO, no debe ir en un servidor git público.
# Python ofrece varias formas de almacenar esto de forma segura, que
# no cubriremos aquí.
app.config['SECRET_KEY'] = 'abcdefghijklmnopqrstuvwxyz'

@app.route('/prompt', methods=['GET', 'POST'])
def index():
    if request.method == "POST":
        return "bah"
    else:
        return "🦙chat v 1.0! Use POST to ask for a prompt."

@app.route('/response/{token}', methods=['GET'])
def response():
    return "a"

@app.route('/healthcheck', methods=['GET'])
def healthcheck():
    return "loaded"

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
