package com.playermarket.player.controller;

import com.playermarket.player.exception.ExceptionHandler;
import com.playermarket.player.model.PlayerRequest;
import com.playermarket.player.model.PlayerResponse;
import com.playermarket.player.service.PlayerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Set of endpoints for players REST service operations")
public class PlayerApiImpl implements PlayerApi {
    @Autowired
    PlayerService playerService;

    @Override
    public ResponseEntity<PlayerResponse> createPlayer(PlayerRequest playerRequest) {
        try {
            PlayerResponse playerResponse = playerService.createPlayer(playerRequest);
            return ResponseEntity.ok(playerResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }

    @Override
    public ResponseEntity<PlayerResponse> getPlayerDetails(Long id) {
        try {
            PlayerResponse playerResponse = playerService.getPlayerDetails(id);
            return ResponseEntity.ok(playerResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }

    @Override
    public ResponseEntity<PlayerResponse> updatePlayer(Long id, PlayerRequest playerRequest) {
        try {
            PlayerResponse playerResponse = playerService.updatePlayer(id, playerRequest);
            return ResponseEntity.ok(playerResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }

    @Override
    public ResponseEntity<PlayerResponse> deletePlayer(Long id) {
        try {
            PlayerResponse playerResponse = playerService.deletePlayer(id);
            return ResponseEntity.ok(playerResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }
}
