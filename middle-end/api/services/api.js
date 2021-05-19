const axios = require('axios');
const adapter = require("axios/lib/adapters/http")

axios.defaults.adapter = adapter;

class API {

    constructor(url) {
        if (url === undefined || url === "") {
            url = 'http://localhost:8086';
        }
        this.url = url
    }

    withPath(path) {
        if (!path.startsWith("/")) {
            path = "/" + path
        }
        return `${this.url}${path}`
    }

    async getPayment(body) {
        return axios.post(this.withPath("/engine/consolidate"), body, {
            headers: {
                "Content-Type": 'application/json'
            }
        }).then(r => r.data);
    }
}

exports.API = API;
