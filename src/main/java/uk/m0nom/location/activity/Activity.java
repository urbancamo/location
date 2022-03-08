package uk.m0nom.location.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import uk.m0nom.location.coords.GlobalCoords3D;

/**
 * An activity is any amateur radio activity programme or awards programme that you can participate in.
 * This class captures the typical common data set for an activity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@DynamoDBTable(tableName = "activity")
public class Activity {
    public final static String TABLE_NAME = "activity";

    @DynamoDBHashKey
    private String type;

    @DynamoDBRangeKey
    private String ref;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private Double latitude;

    @DynamoDBAttribute
    private Double longitude;

    @DynamoDBAttribute
    private Double altitude;

    @DynamoDBAttribute
    private String grid;

    public Activity(String type) {
        this.type = type;
    }
}
