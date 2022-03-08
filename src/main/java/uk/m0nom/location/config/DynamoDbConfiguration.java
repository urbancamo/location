package uk.m0nom.location.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import uk.m0nom.location.ProfileManager;
import uk.m0nom.location.activity.CsvActivityReader;

import java.util.logging.Logger;

@Configuration
public class DynamoDbConfiguration implements EnvironmentAware {

    private static final Logger logger = Logger.getLogger(DynamoDbConfiguration.class.getName());
    private static Environment env;

    private final AwsEndpointConfiguration endpointConfiguration;

    public DynamoDbConfiguration(AwsEndpointConfiguration endpointConfiguration) {
        this.endpointConfiguration = endpointConfiguration;
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    @Bean
    public AmazonDynamoDB dynamoDBClient() {
        return buildAmazonDynamoDB();
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        logger.info(String.format("Building DynamoDB Configuration using profile: %s", new ProfileManager().getActiveProfiles()));

        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration.getEndpoint()
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        getProperty("AWS_ACCESS_KEY"),
                                        getProperty("AWS_SECRET_KEY")
                                )
                        )
                )
                .build();
    }

    public static String getProperty(String key) {
        return env.getProperty(key);
    }

    @Override
    public void setEnvironment(Environment env) {
        DynamoDbConfiguration.env = env;
    }
}
