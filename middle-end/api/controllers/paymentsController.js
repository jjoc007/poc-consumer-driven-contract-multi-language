'use strict';

const api = require('../services/api').API;
const service = new api('http://localhost:8086');

exports.list_payments = function(req, res) {
    console.log(req.body)
    return service.getPayment(req.body).then((data)=> {
        res.json(data);
    })
};
