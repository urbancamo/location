package uk.m0nom.location.repository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.stereotype.Repository;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.ActivityDatabase;
import uk.m0nom.location.activity.ActivityDatabases;
import uk.m0nom.location.activity.cota.Cota;
import uk.m0nom.location.activity.gma.Gma;
import uk.m0nom.location.activity.hema.Hema;
import uk.m0nom.location.activity.lota.Lota;
import uk.m0nom.location.activity.pota.Pota;
import uk.m0nom.location.activity.rota.Rota;
import uk.m0nom.location.activity.sota.Sota;
import uk.m0nom.location.activity.wota.Wota;
import uk.m0nom.location.activity.wwff.Wwff;

import java.util.logging.Logger;

@Repository
public class ActivityRepository {
    private static final Logger logger = Logger.getLogger(ActivityRepository.class.getName());


    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB dynamoDBClient;

    public ActivityRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDBClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBClient = dynamoDBClient;
    }
    
    public Activity save(Activity activity) {
        dynamoDBMapper.save(activity);
        return activity;
    }

    public Cota save(Cota data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Gma save(Gma data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Hema save(Hema data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Lota save(Lota data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Pota save(Pota data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Rota save(Rota data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Sota save(Sota data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Wota save(Wota data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Wwff save(Wwff data) {
        dynamoDBMapper.save(data);
        return data;
    }

    public Activity getActivityByPk(String type, String ref) {
        return dynamoDBMapper.load(Activity.class, type, ref);
    }

    public Cota getCotaByPk(String ref) {
        return dynamoDBMapper.load(Cota.class, "COTA", ref);
    }

    public Sota getSotaByPk(String ref) {
        return dynamoDBMapper.load(Sota.class, "SOTA", ref);
    }

    public String delete(String type, String ref) {
        Activity activity = dynamoDBMapper.load(Activity.class, type, ref);
        dynamoDBMapper.delete(activity);
        return "Activity Deleted!";
    }

    public String update(String type, String ref, Activity Activity) {
        dynamoDBMapper.save(Activity,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("type",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(type)
                                ))
                        .withExpectedEntry("ref",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(ref)
                                ))
        );
        return ref;
    }

    public void deleteActivityTable() throws AmazonServiceException {
        DeleteTableRequest request = new DeleteTableRequest().withTableName("activity");
        dynamoDBClient.deleteTable(request);
    }

    public void createActivityTable() throws AmazonServiceException {
        CreateTableRequest request = new CreateTableRequest()
                .withTableName("activity")
                .withKeySchema(
                        new KeySchemaElement("type", KeyType.HASH),
                        new KeySchemaElement("ref", KeyType.RANGE))
                .withAttributeDefinitions(
                        new AttributeDefinition("type", ScalarAttributeType.S),
                        new AttributeDefinition("ref", ScalarAttributeType.S))
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L));
        dynamoDBClient.createTable(request);
    }

    public void seedActivityTable() throws AmazonServiceException {
        // Load all activities from CSV files
        ActivityDatabases databases = new ActivityDatabases();
        databases.loadData();
        ActivityDatabase db = databases.getDatabase(Cota.TYPE);
        for (Activity info : db.getValues()) {
            save((Cota) info);
        }

        db = databases.getDatabase(Gma.TYPE);
        for (Activity info : db.getValues()) {
            save((Gma) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

        db = databases.getDatabase(Hema.TYPE);
        for (Activity info : db.getValues()) {
            save((Hema) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

        db = databases.getDatabase(Lota.TYPE);
        for (Activity info : db.getValues()) {
            save(info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

        db = databases.getDatabase(Pota.TYPE);
        for (Activity info : db.getValues()) {
            save((Pota) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

        db = databases.getDatabase(Rota.TYPE);
        for (Activity info : db.getValues()) {
            save((Rota) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

        db = databases.getDatabase(Sota.TYPE);
        for (Activity info : db.getValues()) {
            save((Sota) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

        db = databases.getDatabase(Wota.TYPE);
        for (Activity info : db.getValues()) {
            save((Wota) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

        db = databases.getDatabase(Wwff.TYPE);
        for (Activity info : db.getValues()) {
            save((Wwff) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));
        logger.info("Seeding finished");
    }
}
