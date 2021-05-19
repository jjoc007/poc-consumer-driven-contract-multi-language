package co.com.poc.cdc.engine.service;

import co.com.poc.cdc.engine.model.User;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

	private final RestTemplate restTemplate;

	public UserServiceClient(@Qualifier("userRestTemplate") RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public User findUser(String userId) {
		return restTemplate.getForObject("/users/" + userId, User.class);
	}
}
