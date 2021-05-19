package co.com.poc.cdc.engine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Consolidate {
    private String userID;
    private String paymentID;
}
