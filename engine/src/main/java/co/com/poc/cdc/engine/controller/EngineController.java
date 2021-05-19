package co.com.poc.cdc.engine.controller;

import co.com.poc.cdc.engine.model.Consolidate;
import co.com.poc.cdc.engine.model.Payment;
import co.com.poc.cdc.engine.model.Response;
import co.com.poc.cdc.engine.model.User;
import co.com.poc.cdc.engine.service.PaymentServiceClient;
import co.com.poc.cdc.engine.service.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EngineController {

	private final UserServiceClient userService;
	private final PaymentServiceClient paymentService;

	@PostMapping("/engine/consolidate")
	public Response consolidate(@RequestBody Consolidate consolidate) {
		final User user= userService.findUser(consolidate.getUserID());
		final Payment payment= paymentService.findPayment(consolidate.getPaymentID());
		System.out.println(user);
		System.out.println(payment);
		return Response.builder()
			.payment(payment)
			.user(user)
			.build();
	}
}
