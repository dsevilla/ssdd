FROM python:3.7-slim

RUN mkdir -p /videofaces-frontend

MAINTAINER Your Name "youremail@domain.tld"

# We copy just the requirements.txt first to leverage Docker cache
COPY requirements.txt /videofaces-frontend

WORKDIR /videofaces-frontend

RUN pip3 install -r requirements.txt

ENV FLASK_APP="app/app.py"
ENV FLASK_ENV="development"

EXPOSE 5000

CMD [ "flask", "run", "--host=0.0.0.0" ]
