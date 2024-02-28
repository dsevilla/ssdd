FROM python:3.10-slim

COPY . /ssdd-frontend

WORKDIR /ssdd-frontend

RUN pip install -r requirements.txt

ENV FLASK_APP="app/app.py"
ENV FLASK_DEBUG=true
ENV PORT=5010

EXPOSE 5010

CMD [ "flask", "run", "--host=0.0.0.0", "--port=5010" ]
