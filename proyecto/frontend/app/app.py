from flask import Flask, render_template, send_from_directory, url_for, request, redirect
from flask_login import LoginManager, login_manager, current_user, login_user, login_required, logout_user
import requests
import os

# Usuarios
from models import users, User

# Login
from forms import LoginForm, RegisterForm

import logging

app = Flask(__name__, static_url_path='')
login_manager = LoginManager()
login_manager.init_app(app) # Para mantener la sesión

# Configurar el secret_key. OJO, no debe ir en un servidor git público.
# Python ofrece varias formas de almacenar esto de forma segura, que
# no cubriremos aquí.
app.config['SECRET_KEY'] = 'qH1vprMjavek52cv7Lmfe1FoCexrrV8egFnB21jHhkuOHm8hJUe1hwn7pKEZQ1fioUzDb3sWcNK1pJVVIhyrgvFiIrceXpKJBFIn_i9-LTLBCc4cqaI3gjJJHU6kxuT8bnC7Ng'

@app.route('/static/<path:path>')
def serve_static(path):
    return send_from_directory('static', path)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    if current_user.is_authenticated:
        return redirect(url_for('index'))
    else:
        error = None
        form = LoginForm(None if request.method != 'POST' else request.form)
        if request.method == "POST" and form.validate():
            if form.email.data != 'admin@um.es' or form.password.data != 'admin':
                error = 'Invalid Credentials. Please try again.'
            else:
                user = User(1, 'admin', form.email.data.encode('utf-8'),
                            form.password.data.encode('utf-8'))
                users.append(user)
                login_user(user, remember=form.remember_me.data)
                return redirect(url_for('index'))

        return render_template('login.html', form=form,  error=error)

@app.route('/profile')
@login_required
def profile():
    return render_template('profile.html')

@app.route('/register', methods=['GET', 'POST'])
def register():
    if current_user.is_authenticated:
        return redirect(url_for('index'))
    else:
        error = None
        form = RegisterForm()
        if request.method == "POST":
            if form.validate_on_submit():
                query_url = ('http://backend-rest:8080/Service/register')
                userdata = {
                    'id' : form.id.data,
                    'name' : form.username.data, 
                    'email' : form.email.data,
                    'password' : form.password.data
                }
                headers = {'Content-Type': 'application/json'}

                logging.info("Formulario validado")
                logging.info(str(userdata))
                r = requests.post(query_url, json=userdata, headers=headers)
                logging.info(r.text)

                if r.status_code == 201:
                    id_obj = r.json() 
                    user_id = id_obj['id']
                    user = User(user_id, form.username.data, form.email.data, form.password.data)
                    logging.info('usuario registrado con id ' + str(user_id))

                    users.append(user)
                    return redirect(url_for('login'))
                if r.status_code == 401: 
                    error = 'Error: El usuario ya existe.'
                    # flash(error)
                    return redirect(url_for('register'))
                else:
                    # flash("Error en el registro. Inténtalo de nuevo.", "danger")
                    return redirect(url_for('register'))
                
                # if r.status_code == 401: 
                #     error = 'El usuario ya existe.'
                #     flash(error)
                #     render_template('signup.html', form=form,  error=error)
                # else:
                #     print('xd')
            else: 
                for field_name, errors in form.errors.items():
                    for error in errors:
                        print(f"Error en el campo '{field_name}': {error}")

        return render_template('register.html', form=form,  error=error)


@app.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('index'))

@login_manager.user_loader
def load_user(user_id):
    for user in users:
        if user.id == int(user_id):
            return user
    return None

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=int(os.environ.get('PORT', 5010)))
