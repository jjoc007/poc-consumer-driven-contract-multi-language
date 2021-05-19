package co.com.poc.cdc.engine.model;

import java.util.Date;
import lombok.Data;

@Data
public class User {
    private String id;
    private String legacyId;
    private String name;
}
