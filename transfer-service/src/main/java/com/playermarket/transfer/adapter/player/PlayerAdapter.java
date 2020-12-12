package com.playermarket.transfer.adapter.player;

import com.playermarket.transfer.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PlayerAdapter {
    @Value("${player-service.url}")
    String playerService;

    @Autowired
    RestTemplate restTemplate;

    public void updatePlayer(Long playerId, Long teamId) {
        Player player = new Player();
        player.setTeamId(teamId);
        HttpEntity<Player> request = new HttpEntity<>(player);

        restTemplate.put(playerService + "/player/" + playerId, request, Player.class);
    }

    public ResponseEntity<Player> getPlayer(Long playerId) {
        return restTemplate.getForEntity(playerService + "/player/" + playerId, Player.class);
    }
}
