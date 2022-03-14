package com.example.demo.controller;

import com.example.demo.domain.Player;
import com.example.demo.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
@AutoConfigureMockMvc
class PlayerControllerTest {
    @MockBean
    PlayerService playerService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllPlayers() throws Exception {
        List<Player> playerList = new ArrayList<>();
        Player player = new Player();
        player.setName("Anil");
        Player player1 = new Player();
        player1.setName("Anil2");

        playerList.add(player);
        playerList.add(player1);

        Mockito.when(playerService.getAllPlayer()).thenReturn(playerList);

        MvcResult mvcResult = mockMvc.perform(get("/api/players")).andReturn();
        String result = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(true, result.contains("Anil"));
        Assertions.assertEquals(true, result.contains("Anil2"));


    }

    @Test
    void getPlayerById() throws Exception {
        Player player = new Player();
        player.setPlayerID("123");
        player.setName("Anil");
        Mockito.when(playerService.getPlayer("123")).thenReturn(Optional.of(player));

        MvcResult mvcResult = mockMvc.perform(get("/api/players/123")).andReturn();
        boolean result = mvcResult.getResponse().getContentAsString().contains("Anil");

        Assertions.assertEquals(true, result);
    }

    @Test
    void TestExcption(){
        Mockito.when(playerService.getAllPlayer()).thenReturn(null);
        Exception exception = (Exception) Assertions.assertThrows(NullPointerException.class, () -> mockMvc.perform(get("/api/players")).andReturn());
        //As here no exption was thrown as we are catchint the excption esle it would have worked
        String msg = exception.getMessage();
        Assertions.assertTrue(msg.contains("Sorryyyyy"));
    }
}