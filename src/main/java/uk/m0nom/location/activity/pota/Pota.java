package uk.m0nom.location.activity.pota;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import uk.m0nom.location.activity.Activity;

/**
 * Additional information for a Park on the Air
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Pota extends Activity {
    public final static String TYPE = "POTA";

    @DynamoDBAttribute
    private Boolean active;

    @DynamoDBAttribute
    private Integer entityId;

    @DynamoDBAttribute
    private String locationDesc;

    public Pota() {
        super(TYPE);
    }
}
