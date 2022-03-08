package uk.m0nom.location.repository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.stereotype.Repository;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.ActivityDatabase;
import uk.m0nom.location.activity.ActivityDatabases;
import uk.m0nom.location.activity.cota.Cota;
import uk.m0nom.location.activity.gma.Gma;
import uk.m0nom.location.activity.hema.Hema;
import uk.m0nom.location.activity.iota.Iota;
import uk.m0nom.location.activity.lota.Lota;
import uk.m0nom.location.activity.pota.Pota;
import uk.m0nom.location.activity.rota.Rota;
import uk.m0nom.location.activity.sota.Sota;
import uk.m0nom.location.activity.wota.Wota;
import uk.m0nom.location.activity.wwff.Wwff;

import java.util.ArrayList;
import java.util.Iterator;
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

    public Iota save(Iota data) {
        dynamoDBMapper.save(data);
        return data;
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

    public String findActivityTypeByRef(String ref) {
        String activity = null;
        DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
        Table table = dynamoDB.getTable(Activity.TABLE_NAME);

        // Note using the secondary table index as we aren't supplied with the partition key
        Index index = table.getIndex("RefIndex");

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("#r = :v_ref")
                .withNameMap(new NameMap()
                        .with("#r", "ref"))
                .withValueMap(new ValueMap()
                        .withString(":v_ref",ref));

        ItemCollection<QueryOutcome> items;
        Iterator<Item> iterator;
        Item item = null;

        try {
            items = index.query(spec);

            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
            }
            assert item != null;
            activity = item.getString("type");
        }
        catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return activity;
    }

    public Cota getCotaByPk(String ref) {
        return dynamoDBMapper.load(Cota.class, Cota.TYPE, ref);
    }

    public Gma getGmaByPk(String ref) {
        return dynamoDBMapper.load(Gma.class, Gma.TYPE, ref);
    }

    public Hema getHemaByPk(String ref) {
        return dynamoDBMapper.load(Hema.class, Hema.TYPE, ref);
    }

    public Iota getIotaByPk(String ref) {
        return dynamoDBMapper.load(Iota.class, Iota.TYPE, ref);
    }

    public Lota getLotaByPk(String ref) {
        return dynamoDBMapper.load(Lota.class, Lota.TYPE, ref);
    }

    public Pota getPotaByPk(String ref) {
        return dynamoDBMapper.load(Pota.class, Pota.TYPE, ref);
    }

    public Rota getRotaByPk(String ref) {
        return dynamoDBMapper.load(Rota.class, Rota.TYPE, ref);
    }

    public Sota getSotaByPk(String ref) {
        return dynamoDBMapper.load(Sota.class, Sota.TYPE, ref);
    }

    public Wota getWotaByPk(String ref) {
        return dynamoDBMapper.load(Wota.class, Wota.TYPE, ref);
    }

    public Wwff getWwffByPk(String ref) {
        return dynamoDBMapper.load(Wwff.class, Wwff.TYPE, ref);
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
        GlobalSecondaryIndex refIndex = new GlobalSecondaryIndex()
                .withIndexName("RefIndex")
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits((long) 10)
                        .withWriteCapacityUnits((long) 1))
                .withProjection(new Projection().withProjectionType(ProjectionType.ALL));

        ArrayList<KeySchemaElement> indexKeySchema = new ArrayList<>();

        indexKeySchema.add(new KeySchemaElement()
                .withAttributeName("ref")
                .withKeyType(KeyType.HASH));  //Partition key
        indexKeySchema.add(new KeySchemaElement()
                .withAttributeName("type")
                .withKeyType(KeyType.RANGE));  //Sort key
        refIndex.setKeySchema(indexKeySchema);

        CreateTableRequest request = new CreateTableRequest()
                .withTableName("activity")
                .withKeySchema(
                        new KeySchemaElement("type", KeyType.HASH),
                        new KeySchemaElement("ref", KeyType.RANGE))
                .withAttributeDefinitions(
                        new AttributeDefinition("type", ScalarAttributeType.S),
                        new AttributeDefinition("ref", ScalarAttributeType.S))
                .withGlobalSecondaryIndexes(refIndex)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L));
        dynamoDBClient.createTable(request);
    }

    public void seedActivityTable() throws AmazonServiceException {
        // Load all activities from CSV files
        ActivityDatabases databases = new ActivityDatabases();
        databases.loadData();

        ActivityDatabase db = databases.getDatabase(Iota.TYPE);
        for (Activity info : db.getValues()) {
            save((Iota) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

        db = databases.getDatabase(Cota.TYPE);
        for (Activity info : db.getValues()) {
            save((Cota) info);
        }
        logger.info(String.format("Seeded %d %s records", db.getValues().size(), db.getType()));

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
