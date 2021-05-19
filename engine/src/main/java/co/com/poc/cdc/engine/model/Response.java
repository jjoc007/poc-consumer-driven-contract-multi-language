package co.com.poc.cdc.engine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private User user;
    private Payment payment;
}
