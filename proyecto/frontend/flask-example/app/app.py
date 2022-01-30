from flask import Flask, render_template, send_from_directory
app = Flask(__name__, static_url_path='')

@app.route('/')
def hello_world():
    return render_template('index.html', name="diego")

@app.route('/static/<path:path>')
def serve_static(path):
    return send_from_directory('static', path)

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
