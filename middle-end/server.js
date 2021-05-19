const bodyParser = require('body-parser');

const express = require('express'),
    app = express(),
    port = 8088;

const routes = require('./api/routes/routes'); //importing route

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

routes(app); //register the route

app.listen(port);

console.log('todo list RESTful API server started on: ' + port);
