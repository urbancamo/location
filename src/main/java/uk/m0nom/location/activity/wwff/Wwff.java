package uk.m0nom.location.activity.wwff;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import uk.m0nom.location.activity.Activity;

import java.util.Date;

/**
 * Additional information stored for a Worldwide Flora Fauna location
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Wwff extends Activity {
    public final static String TYPE = "WWFF";

    @DynamoDBAttribute
    private Boolean active;

    @DynamoDBAttribute
    private String program;

    @DynamoDBAttribute
    private String dxcc;

    @DynamoDBAttribute
    private String state;

    @DynamoDBAttribute
    private String county;

    @DynamoDBAttribute
    private String continent;

    @DynamoDBAttribute
    private String iota;

    @DynamoDBAttribute
    private String iaruLocator;


    @DynamoDBAttribute
    private String IUCNcat;

    @DynamoDBAttribute
    private String validFrom;

    @DynamoDBAttribute
    private String validTo;

    @DynamoDBAttribute
    private String notes;

    @DynamoDBAttribute
    private String lastMod;

    @DynamoDBAttribute
    private String changeLog;

    @DynamoDBAttribute
    private String reviewFlag;

    @DynamoDBAttribute
    private String specialFlags;

    @DynamoDBAttribute
    private String website;

    @DynamoDBAttribute
    private String country;

    @DynamoDBAttribute
    private String region;

    public Wwff() {
        super(TYPE);
    }
}
