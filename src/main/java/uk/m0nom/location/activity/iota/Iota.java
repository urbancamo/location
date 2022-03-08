package uk.m0nom.location.activity.iota;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.coords.GlobalCoords3D;
import uk.m0nom.location.coords.LocationAccuracy;
import uk.m0nom.location.coords.LocationSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Top of the information hierarchy stored for any Island on the Air reference
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "activity")
public class Iota extends Activity {
    public final static String TYPE = "IOTA";

    public Iota() {
        super(Iota.TYPE);
        subGroups = new ArrayList<>();
    }


    @DynamoDBAttribute
    private String dxccNum;

    @DynamoDBAttribute
    private Double latitudeMax;

    @DynamoDBAttribute
    private Double latitudeMin;

    @DynamoDBAttribute
    private Double longitudeMax;

    @DynamoDBAttribute
    private Double longitudeMin;

    @DynamoDBAttribute
    private String groupRegion;

    @DynamoDBAttribute
    private Integer whitelist;

    @DynamoDBAttribute
    private Double pcCredited;

    @DynamoDBAttribute
    private String comment;

    @DynamoDBAttribute
    @DynamoDBTypeConvertedJson
    private List<IotaSubGroup> subGroups;
}
