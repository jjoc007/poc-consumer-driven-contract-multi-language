package co.com.poc.cdc.engine.model;

import lombok.Data;

@Data
public class Payment {
    private String id;
    private String name;
    private String amount;
    private String category;
}
