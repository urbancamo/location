package uk.m0nom.location.activity.rota;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import uk.m0nom.location.activity.Activity;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Rota extends Activity {
    public final static String TYPE = "ROTA";

    @DynamoDBAttribute
    private String club;

    @DynamoDBAttribute
    private String wab;

    @DynamoDBAttribute
    private String grid;

    public Rota() {
        super(TYPE);
    }
}
