package Models;

import java.util.Date;
import java.util.UUID;

public class BaseClass {
    protected String id;
    protected Date lastModifiedAt, createdAt;
    protected String lastModifiedBy, createdBy;
    BaseClass(){
        id = UUID.randomUUID().toString();
        createdAt = lastModifiedAt = new Date();
        lastModifiedBy = createdBy = new String("System");
    }
}
