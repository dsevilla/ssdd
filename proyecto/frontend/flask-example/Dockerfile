FROM ubuntu:20.04

MAINTAINER Your Name "youremail@domain.tld"

RUN apt-get update -y && \
    apt-get install -y python3-pip python3-dev vim-tiny

# We copy just the requirements.txt first to leverage Docker cache
COPY ./requirements.txt /flask-example/requirements.txt

WORKDIR /flask-example

RUN pip install -r requirements.txt

COPY . /flask-example

ENV FLASK_APP="app/app.py"
ENV FLASK_ENV="development"

CMD [ "flask", "run", "--host=0.0.0.0" ]
