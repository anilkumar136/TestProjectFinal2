package com.example.demo.service;

import com.example.demo.domain.Player;
import com.example.demo.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
class PlayerServiceImplTest {

    @MockBean
    PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

    @Test
    void getAllPlayer() {
        List<Player> playerList = new ArrayList<>();
        Player player1= new Player();
        Player player2 = new Player();

        playerList.addAll(Arrays.asList(player1,player2));

        Mockito.when(playerRepository.findAll()).thenReturn(playerList);

        List<Player> playerList1 = playerService.getAllPlayer();

        Assertions.assertEquals(playerList.size(), playerList1.size());

    }

    @Test
    void getPlayerByPage() {
    }

    @Test
    void create() {
    }

    @Test
    void getPlayer() {
    }

    @Test
    void incWeight() {
    }

    @Test
    void incHeight() {
    }
}