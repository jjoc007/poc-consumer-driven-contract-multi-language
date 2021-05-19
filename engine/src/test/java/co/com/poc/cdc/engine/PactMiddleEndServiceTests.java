package co.com.poc.cdc.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import co.com.poc.cdc.engine.model.Payment;
import co.com.poc.cdc.engine.model.User;
import co.com.poc.cdc.engine.service.PaymentServiceClient;
import co.com.poc.cdc.engine.service.UserServiceClient;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import au.com.dius.pact.provider.junitsupport.Provider;

@Provider("engine-service")
@PactBroker(
	host = "localhost",
	port = "8000",
	authentication = @PactBrokerAuth(username = "pact_workshop", password = "pact_workshop")
)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PactMiddleEndServiceTests {

	@LocalServerPort
	int port;

	@MockBean
	private UserServiceClient userServiceClient;

	@MockBean
	private PaymentServiceClient paymentServiceClient;

	@BeforeEach
	void setUp(PactVerificationContext context) {
		context.setTarget(new HttpTestTarget("localhost", port));
	}

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	void verifyPact(PactVerificationContext context, HttpRequest request) {
		context.verifyInteraction();
	}

	@State("ID 1 with payment pencil exists")
	void toUserExistState() {
		User user =  new User();
		user.setId("123456789");
		user.setLegacyId("123456789");
		user.setName("Juan");
		when(userServiceClient.findUser(Mockito.anyString())).thenReturn(user);

		Payment payment = new Payment();
		payment.setCategory("office");
		payment.setId("pencil");
		payment.setAmount("10000");
		payment.setName("pencil-123");

		when(paymentServiceClient.findPayment(Mockito.anyString())).thenReturn(payment);
	}
}
