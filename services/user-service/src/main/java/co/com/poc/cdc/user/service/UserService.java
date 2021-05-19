package co.com.poc.cdc.user.service;

import co.com.poc.cdc.user.model.User;
import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public User findUser(String userId) {
		return User.builder()
			.id(userId)
			.legacyId(UUID.randomUUID().toString())
			.name("Beth")
			.build();
	}
}
