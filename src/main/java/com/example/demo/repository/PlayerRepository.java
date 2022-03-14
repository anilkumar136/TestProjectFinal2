package com.example.demo.repository;

import com.example.demo.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player,String> {
    Optional<Player> findByPlayerID(String playerID);
}
