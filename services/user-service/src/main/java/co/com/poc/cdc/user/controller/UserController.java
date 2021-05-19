package co.com.poc.cdc.user.controller;

import co.com.poc.cdc.user.model.User;
import co.com.poc.cdc.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable String userId) {
		return userService.findUser(userId);
	}
}
