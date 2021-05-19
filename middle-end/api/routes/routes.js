'use strict';
module.exports = function(app) {
    const paymentController = require('../controllers/paymentsController');

    app.route('/payments')
        .post(paymentController.list_payments);
};
