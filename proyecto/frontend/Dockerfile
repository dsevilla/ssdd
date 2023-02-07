FROM python:3.10-slim

COPY . /ssdd-frontend

WORKDIR /ssdd-frontend

RUN pip install -r requirements.txt

ENV FLASK_APP="app/app.py"
ENV FLASK_DEBUG=true

EXPOSE 5000

CMD [ "flask", "run", "--host=0.0.0.0" ]
