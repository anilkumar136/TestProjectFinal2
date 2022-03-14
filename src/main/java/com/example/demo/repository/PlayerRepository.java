package com.example.demo.repository;

import com.example.demo.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*   If we want to run custem Queryy we Need MongoTemplate
@Configuration
public class SimpleMongoConfig {

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
          .applyConnectionString(connectionString)
          .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "test");
    }
}

After this custem Querry can be like below:
Query query = new Query();
query.addCriteria(Criteria.where("name").is("Eric"));
List<User> users = mongoTemplate.find(query, User.class);

Reguler expresssion
Query query = new Query();
query.addCriteria(Criteria.where("name").regex("^A"));
List<User> users = mongoTemplate.find(query,User.class);

lt and gt
Query query = new Query();
query.addCriteria(Criteria.where("age").lt(50).gt(20));
List<User> users = mongoTemplate.find(query,User.class);

sort
Query query = new Query();
query.with(Sort.by(Sort.Direction.ASC, "age"));
List<User> users = mongoTemplate.find(query,User.class);
 */

@Repository
public interface PlayerRepository extends MongoRepository<Player,String> {
    Optional<Player> findByPlayerID(String playerID);
}
