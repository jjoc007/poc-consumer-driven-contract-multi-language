package co.com.poc.cdc.engine.service;

import co.com.poc.cdc.engine.model.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceClient {

	private final RestTemplate restTemplate;

	public PaymentServiceClient(@Qualifier("paymentRestTemplate") RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Payment findPayment(String paymentID) {
		return restTemplate.getForObject("/payment/" + paymentID, Payment.class);
	}
}
