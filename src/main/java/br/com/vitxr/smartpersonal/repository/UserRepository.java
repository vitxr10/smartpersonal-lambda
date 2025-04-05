package br.com.vitxr.smartpersonal.repository;

import br.com.vitxr.smartpersonal.entity.User;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
    DynamoDBMapper mapper = new DynamoDBMapper(db);

    public User create(User user) {
//        user.setActive(true);
        mapper.save(user);
        return user;
    }

    public List<User> getAll() {
        System.out.println("caiu no repository");
        return mapper.scan(User.class, new DynamoDBScanExpression());
    }

    public User getById(String id) {
        return mapper.load(User.class, id);
    }

    public void delete(User user) {
        mapper.delete(user);
    }

    public User update(User user) {
        mapper.save(user, buildExpression(user));
        return user;
    }

    private DynamoDBSaveExpression buildExpression(User user) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("id", new ExpectedAttributeValue(new AttributeValue().withS(String.valueOf(user.getId()))));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
