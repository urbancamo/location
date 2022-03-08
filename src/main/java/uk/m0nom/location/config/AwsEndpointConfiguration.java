package uk.m0nom.location.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AwsEndpointConfiguration {
    @Value("${aws.dynamodb.serviceEndpoint}")
    private String serviceEndPoint;

    @Value("${aws.dynamodb.signingRegion}")
    private String signingRegion;

    public AwsClientBuilder.EndpointConfiguration getEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(
                serviceEndPoint,
                signingRegion
        );
    }
}
