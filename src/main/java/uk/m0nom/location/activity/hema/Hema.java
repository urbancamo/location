package uk.m0nom.location.activity.hema;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import uk.m0nom.location.activity.Activity;

/**
 * Encapsulates the additional information stored about a HEMA summit
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Hema extends Activity {
    public final static String TYPE = "HEMA";

    @DynamoDBAttribute
    private Integer key;

    @DynamoDBAttribute
    private Boolean active;

    public Hema() {
        super(TYPE);
    }
}
