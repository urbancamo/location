package uk.m0nom.location.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfiguration {

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    @Bean
    public AmazonDynamoDB dynamoDBClient() {
        return buildAmazonDynamoDB();
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(localEndPoint()
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        "dummy",
                                        "dummy"
                                )
                        )
                )
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration cloudEndPoint() {
        return new AwsClientBuilder.EndpointConfiguration(
                "dynamodb.eu-west-2.amazonaws.com",
                "eu-west-2"
        );
    }

    private AwsClientBuilder.EndpointConfiguration localEndPoint() {
        return new AwsClientBuilder.EndpointConfiguration(
                "http://localhost:8000",
                "eu-west-2"
        );
    }
}
