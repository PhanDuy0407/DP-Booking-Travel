FROM python:3.9
WORKDIR /code
COPY ./requirement.txt /code/requirements.txt
RUN pip install -r /code/requirements.txt
COPY ./ /code
CMD ["uvicorn", "return_statement:app", "--host", "0.0.0.0", "--port", "8000"]
# uvicorn return_statement:app --host 0.0.0.0 --port 8000 --reload
