package com.example.demo.service;

import com.example.demo.domain.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
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
