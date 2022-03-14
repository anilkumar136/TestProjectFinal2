package com.example.demo.controller;

import com.example.demo.domain.Player;
import com.example.demo.service.PlayerService;
import com.example.demo.service.PlayerServiceImpl;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {
    Logger logger = LoggerFactory.getLogger(PlayerController.class);

    RateLimiter  rateLimiter = RateLimiter.create(0.25);

    @Autowired
    PlayerService playerService;

    //TODO: will resturn 20k players ...need pagin??? Day2
    @GetMapping("/api/players")
    ResponseEntity getAllPlayers(){
        try {
            List<Player> playerList = playerService.getAllPlayer();
            if (playerList.isEmpty()) {
                return new ResponseEntity("{\"message\": \"No players found\"}", HttpStatus.OK);
            }
            return new ResponseEntity(playerList, HttpStatus.OK);
        }
        catch(Exception e){  //TODO: custem excpetions??
            return new ResponseEntity("{message:Something bad haapend Serser Side....Sorryyyyy}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/api/players/{playerID}")
    ResponseEntity getPlayerById(@PathVariable("playerID") String playerID) {
        try {
            Optional<Player> player = playerService.getPlayer(playerID);
            return getResponseEntity(player, playerID);
        }
        catch(Exception e){
            return new ResponseEntity("Something bad haapend Serser Side....Sorryyyyy",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/api/players/{playerID}/weight")
    ResponseEntity incWeight(@PathVariable("playerID") String playerID){
        try {
            Optional<Player> player = playerService.incWeight(playerID);
            return getResponseEntity(player, playerID);
        }
        catch(Exception e){
            return new ResponseEntity("Something bad haapend Serser Side....Sorryyyyy",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/api/players/{playerID}/height")
    ResponseEntity incHeight(@PathVariable("playerID") String playerID){
        logger.info("test");
        try {
            Optional<Player> player = playerService.incHeight(playerID);
            return getResponseEntity(player, playerID);
        }
        catch(Exception e){
            return new ResponseEntity("Something bad haapend Serser Side....Sorryyyyy",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/players/create")
    //@PostMapping(value = "/api/players/create", consumes= MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity create(@RequestBody Player player){
        try {
            Optional<Player> player1 = playerService.create(player);
            return getResponseEntity(player1, "");
        }
        catch(Exception e){
            return new ResponseEntity("Something bad haapend Serser Side....Sorryyyyy",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/players/getPlayerPage")
    ResponseEntity getPlayerPage(@RequestParam(name="page", defaultValue = "2") int page, @RequestParam(name="size",defaultValue = "2") int size){
        logger.info("Inside this endpoints");
        return new ResponseEntity(playerService.getPlayerByPage(page,size), HttpStatus.OK);
    }

    private ResponseEntity getResponseEntity(Optional<Player> player, String playerID) {
        if (player.isPresent()) {
            return new ResponseEntity(player.get(), HttpStatus.OK);
        }
        return new ResponseEntity("No player with this ID: " + playerID, HttpStatus.OK);
    }

    @GetMapping("/getTest")
    public ResponseEntity getTest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getRemoteAddr();
        String user = request.getRemoteUser();
        if(rateLimiter.tryAcquire()){
            System.out.println(playerService.getPlayer("loducpa01"));
            System.out.println(ip);
            System.out.println(user);
            return new ResponseEntity<>(playerService.getPlayer("loducpa01"), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);

    }



}
