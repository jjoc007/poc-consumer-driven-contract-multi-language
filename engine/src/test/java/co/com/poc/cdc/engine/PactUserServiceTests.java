package co.com.poc.cdc.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import co.com.poc.cdc.engine.model.User;
import co.com.poc.cdc.engine.service.UserServiceClient;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@ExtendWith(PactConsumerTestExt.class)
class PactUserServiceTests {

	@Pact(consumer = "engine-service", provider = "user-service")
	RequestResponsePact getUserByID(PactDslWithProvider builder) {

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		User user = new User();
		user.setId("123456789");
		user.setLegacyId("123456789");
		user.setName("Juan");

		DslPart body = LambdaDsl.newJsonBody((o) -> o
			.stringType("name", "Juan")
				.stringType("id", "123456789")
			.stringType("legacyId", "123456789")
			).build();

		return builder.given("user exist")
			.uponReceiving("get user by ID")
			.method("GET")
			.path("/users/1")
			.willRespondWith()
			.status(200)
			.headers(headers)
			.body(body)
			.toPact();
	}

	@Test
	@PactTestFor(pactMethod = "getUserByID")
	void getUserByID_whenUserExist(MockServer mockServer) {
		User userExpected = new User();
		userExpected.setId("123456789");
		userExpected.setLegacyId("123456789");
		userExpected.setName("Juan");

		RestTemplate restTemplate = new RestTemplateBuilder()
			.rootUri(mockServer.getUrl())
			.build();

		User user = new UserServiceClient(restTemplate).findUser("1");
		assertEquals(userExpected, user);
	}

}
