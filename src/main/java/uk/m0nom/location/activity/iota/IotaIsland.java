package uk.m0nom.location.activity.iota;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Information about an individual island which might be part of a group of islands captured using a single
 * IOTA reference.
 */
@Data
@NoArgsConstructor
@DynamoDBDocument
public class IotaIsland {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("island_name")
    private String name;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("excluded")
    private Integer excluded;
}
