package uk.m0nom.location.activity.cota;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.m0nom.location.activity.Activity;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Cota extends Activity {
    public final static String TYPE = "COTA";

    @DynamoDBAttribute
    private Boolean active;

    @DynamoDBAttribute
    private String noCastles;

    @DynamoDBAttribute
    private String prefix;

    @DynamoDBAttribute
    private String location;

    @DynamoDBAttribute
    private String information;

    public Cota() {
        super(TYPE);
    }
}
