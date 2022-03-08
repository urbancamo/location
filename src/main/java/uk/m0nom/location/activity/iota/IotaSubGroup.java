package uk.m0nom.location.activity.iota;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sub-group of islands that share a common main IOTA reference
 */
@Data
@DynamoDBDocument
public class IotaSubGroup {
    public IotaSubGroup() {
        islands = new ArrayList<>();
    }

    @JsonProperty("subref")
    private String ref;

    @JsonProperty("subname")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("islands")
    private List<IotaIsland> islands;

    public boolean isActive() {
        return "Active".equals(status);
    }
}
