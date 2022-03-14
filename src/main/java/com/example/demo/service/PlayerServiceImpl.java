package com.example.demo.service;

import com.example.demo.domain.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



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


IMPORTANT---we can set read prefrense to  mongoTemplate to direct
read querries to Replicas for permonce....Read prefecne have values like NEAREST
like
template.setReadPreference(ReadPreference.secondary(maxStaleness, TimeUnit.SECONDS));
setting it to go to any seocndary but stale value
 */


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
//    @Cacheable(
//            value = "player",
//            key = "#page",
//            condition = "#page>10")
    public List<Player> getPlayerByPage(int page, int size) {
//        int startIndex = (page-1) * size;
//        List<Player> playerList = getAllPlayer();
//        return playerList.subList(startIndex, startIndex+size);  //Check if start index etc are not out of bound
//
        Pageable pageable = PageRequest.of(page,size);
        Page<Player> requestedPage = playerRepository.findAll(pageable);
        return requestedPage.stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Player> create(Player player) {
        Player player1= playerRepository.save(player);
        return Optional.of(player1);

    }

    @Override
    public Optional<Player> getPlayer(String playerID) {
         return playerRepository.findByPlayerID(playerID);
    }

    @Override
    public Optional<Player> incWeight(String playerID) {
        Optional<Player> player = getPlayer(playerID);
        if(player.isPresent()){
            Player player1 =  player.get();
            int weight = Integer.parseInt(player1.getWeight()) +1;
            player1.setWeight(String.valueOf(weight));

            playerRepository.save(player1);

            return Optional.of(player1);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Player>  incHeight(String playerID) {
        Optional<Player> player = getPlayer(playerID);
        if(player.isPresent()){
            Player player1 =  player.get();
            int height = Integer.parseInt(player1.getHeight()) +1;
            player1.setHeight(String.valueOf(height));

            playerRepository.save(player1);

            return Optional.of(player1);
        }

        return Optional.empty();    }
}
