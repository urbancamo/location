package uk.m0nom.location.activity.gma;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import uk.m0nom.location.activity.Activity;

/**
 * Additional information for a Global Mountain Activity
 */
@Data
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Gma extends Activity {
    public final static String TYPE = "GMA";

    public Gma() {
        super(TYPE);
    }
}
