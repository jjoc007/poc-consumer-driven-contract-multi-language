package co.com.poc.cdc.engine.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EngineServiceConfig {

    @Bean(name = "userRestTemplate")
    RestTemplate userRestTemplate(@Value("${user-service.base-url:http://localhost:8085}") String uri) {
        return new RestTemplateBuilder().rootUri(uri).build();
    }

    @Bean(name = "paymentRestTemplate")
    RestTemplate paymentRestTemplate(@Value("${payment-service.base-url:http://localhost:8087}") String uri) {
        return new RestTemplateBuilder().rootUri(uri).build();
    }
}
