package com.example.demo.service;

import com.example.demo.domain.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
     List<Player> getAllPlayer();
     List<Player> getPlayerByPage(int page, int size);
     Optional<Player> create(Player player);
     Optional<Player> getPlayer(String playerID);
     Optional<Player> incWeight(String playerID);
     Optional<Player> incHeight(String playerID);
}
