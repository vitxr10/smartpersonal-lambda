package br.com.vitxr.smartpersonal.repository;

import br.com.vitxr.smartpersonal.entity.User;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
    DynamoDBMapper mapper = new DynamoDBMapper(db);

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public User create(User user) {
        logger.info("Salvando usuário no banco de dados...");
        user.setActive(true);
        mapper.save(user);
        return user;
    }

    public List<User> getAll() {
        logger.info("Buscando todos usuários no banco de dados...");
        return mapper.scan(User.class, new DynamoDBScanExpression());
    }

    public User getById(String id) {
        logger.info("Buscando usuário de id: {} no banco de dados...", id);
        return mapper.load(User.class, id);
    }

    public boolean delete(String id) {
        logger.info("Buscando usuário com id: {}", id);
        User user = mapper.load(User.class, id);

        if (user == null) {
            return false;
        }

        user.setActive(false);
        return true;
    }

    public User update(User updatedData) {
        logger.info("Buscando usuário com id: {}", updatedData.getId());
        User existing = mapper.load(User.class, updatedData.getId());

        if (existing == null) {
            return null;
        }

        existing.setHeight(updatedData.getHeight());
        existing.setWeight(updatedData.getWeight());

        mapper.save(existing);
        return existing;
    }
}
