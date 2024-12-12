FROM python:3.12-slim

COPY . /ssdd-llamachat

WORKDIR /ssdd-llamachat

RUN pip install --no-cache-dir -r requirements.txt
RUN python initmodel.py

ENV FLASK_APP="app/app.py"
ENV FLASK_DEBUG=true
ENV PORT=5020

EXPOSE 5020

CMD [ "flask", "run", "--host=0.0.0.0", "--port=5020" ]
