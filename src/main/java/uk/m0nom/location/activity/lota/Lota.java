package uk.m0nom.location.activity.lota;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import uk.m0nom.location.activity.Activity;

/**
 * Additional activity information for a Lighthouse or Lightship on the Air
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Lota extends Activity {
    public final static String TYPE = "LOTA";

    @DynamoDBAttribute
    private String country;

    @DynamoDBAttribute
    private String dxcc;

    @DynamoDBAttribute
    private String continent;

    @DynamoDBAttribute
    private String status;

    @DynamoDBAttribute
    private String location;

    public Lota() {
        super(TYPE);
    }
}
