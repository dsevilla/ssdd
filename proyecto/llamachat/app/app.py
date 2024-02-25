from flask import Flask
import os

app = Flask(__name__, static_url_path='')

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
