const path = require("path");
const {Pact} = require("@pact-foundation/pact");
const API = require("./api").API;
const {like} = require("@pact-foundation/pact/dsl/matchers");
const assert = require('assert')


const provider = new Pact({
  consumer: 'middle-end-service',
  provider: 'engine-service',
  log: path.resolve(process.cwd(), 'logs', 'pact.log'),
  logLevel: "warn",
  dir: path.resolve(process.cwd(), 'pacts'),
  spec: 2
});

describe("API Pact test", () => {

  before(() => provider.setup());
  afterEach(() => provider.verify());
  after(() => provider.finalize());

  describe("getting one payment", () => {
    it("ID 1 with payment pencil exists", async () => {

      // set up Pact interactions
      await provider.addInteraction({
        state: 'ID 1 with payment pencil exists',
        uponReceiving: 'get payment with ID 1 with payment pencil',
        withRequest: {
          method: 'POST',
          path: '/engine/consolidate',
          body: {
            "userID": "1",
            "paymentID": "pencil"
          },
          headers: {
            "Content-Type": like("application/json")
          }
        },
        willRespondWith: {
          status: 200,
          headers: {
            'Content-Type': 'application/json'
          },
          body: like({
            "user": {
              "id": "1",
              "legacyId": "9a6d05c0-8346-4efa-97f5-a89b018507b4",
              "name": "Beth"
            },
            "payment": {
              "id": "pencil",
              "name": "pencil-123",
              "amount": "10000",
              "category": "office"
            }
          }),
        },
      });

      const api = new API(provider.mockService.baseUrl);

      const payment = await api.getPayment({
        "userID": "1",
        "paymentID": "pencil"
      });

      assert.ok(payment)
    });

  });
});
