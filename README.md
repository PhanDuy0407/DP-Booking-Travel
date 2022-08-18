# Booking Travel 2022

## 1. Chạy application với docker
```shell
$ docker-compose up
```
## 2. Chạy application local
- Dump data
```shell
$ mysqldump -u {USERNAME} -p dump.sql
```
- Recommender system
```shell
$ cd Recommends_sys
$ source venv/bin/activate
$ pip install -r requirement.txt
$ uvicorn return_statement:app --host 0.0.0.0 --port 8000 --reload
```
- Backend
```shell
$ cd bookingTravel
$ mvn clean install
$ mvn spring-boot:run
```
- Frontend user
```shell
$ cd frontend_user
$ npm install
$ yarn dev
```
- Frontend admin
```shell
$ cd frontend_admin/coreui-free-vue-admin-template-main
$ npm install 
$ npm run serve
```
