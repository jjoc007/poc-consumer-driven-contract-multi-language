package co.com.poc.cdc.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import co.com.poc.cdc.engine.model.Payment;
import co.com.poc.cdc.engine.model.User;
import co.com.poc.cdc.engine.service.PaymentServiceClient;
import co.com.poc.cdc.engine.service.UserServiceClient;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@ExtendWith(PactConsumerTestExt.class)
class PactPaymentServiceTests {

	@Pact(consumer = "engine-service", provider = "payment-service")
	RequestResponsePact getPaymentByID(PactDslWithProvider builder) {

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		Payment payment = new Payment();
		payment.setId("pencil");
		payment.setName("pencil-123");
		payment.setAmount("1000");
		payment.setCategory("office");

		DslPart body = LambdaDsl.newJsonBody((o) -> o
			.stringType("name", payment.getName())
				.stringType("id", payment.getId())
				.stringType("amount", payment.getAmount())
				.stringType("category", payment.getCategory())
			).build();

		return builder.given("payment exist")
			.uponReceiving("get payment by ID")
			.method("GET")
			.path("/payment/pencil")
			.willRespondWith()
			.status(200)
			.headers(headers)
			.body(body)
			.toPact();
	}

	@Test
	@PactTestFor(pactMethod = "getPaymentByID")
	void getPaymentByID_whenUserExist(MockServer mockServer) {
		Payment paymentExp = new Payment();
		paymentExp.setId("pencil");
		paymentExp.setName("pencil-123");
		paymentExp.setAmount("1000");
		paymentExp.setCategory("office");

		RestTemplate restTemplate = new RestTemplateBuilder()
			.rootUri(mockServer.getUrl())
			.build();

		Payment payment = new PaymentServiceClient(restTemplate).findPayment("pencil");
		assertEquals(paymentExp, payment);
	}

}
