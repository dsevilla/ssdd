FROM ubuntu:20.04

MAINTAINER Your Name "youremail@domain.tld"

RUN apt-get update -y && \
    apt-get install -y python3 python3-pip

# We copy just the requirements.txt first to leverage Docker cache
COPY ./requirements.txt /videofaces-frontend/requirements.txt

WORKDIR /videofaces-frontend

RUN pip install -r requirements.txt

COPY . /videofaces-frontend

ENV FLASK_APP="app/app.py"
ENV FLASK_ENV="development"

EXPOSE 5000

CMD [ "flask", "run", "--host=0.0.0.0" ]
