package uk.m0nom.location.activity.wota;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import uk.m0nom.location.activity.Activity;

/**
 * Additional information for a Wainwright on the Air summit
 * Cross-references to SOTA and HEMA summits exist in the WOTA database and have been retained here.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Wota extends Activity {
    public final static String TYPE = "WOTA";

    @DynamoDBAttribute
    private Integer internalId;

    @DynamoDBAttribute
    private String sotaId;

    @DynamoDBAttribute
    private String hemaId;

    @DynamoDBAttribute
    private String book;

    @DynamoDBAttribute
    private String reference;

    @DynamoDBAttribute
    private String gridId;

    @DynamoDBAttribute
    private Integer x;

    @DynamoDBAttribute
    private Integer y;

    public Wota() {
        super(TYPE);
    }
}


