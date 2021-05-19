package co.com.poc.cdc.user;

import static org.mockito.Mockito.when;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import co.com.poc.cdc.user.model.User;
import co.com.poc.cdc.user.service.UserService;
import org.apache.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Provider("user-service")
@PactBroker(
	host = "localhost",
	port = "8000",
	authentication = @PactBrokerAuth(username = "pact_workshop", password = "pact_workshop")
)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

	@LocalServerPort
	int port;

	@MockBean
	private UserService userService;

	@BeforeEach
	void setUp(PactVerificationContext context) {
		context.setTarget(new HttpTestTarget("localhost", port));
	}

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	void verifyPact(PactVerificationContext context, HttpRequest request) {
		context.verifyInteraction();
	}

	@State("user exist")
	void toUserExistState() {
		when(userService.findUser(Mockito.anyString())).thenReturn(
			User.builder()
				.id("123456789")
			.legacyId("123456789")
			.name("Juan")
			.build()
		);
	}

}
