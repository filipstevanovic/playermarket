package com.playermarket.transfer.adapter.team;

import com.playermarket.transfer.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TeamAdapter {
    @Value("${team-service.url}")
    String teamService;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<Team> getTeam(Long teamId) {
        return restTemplate.getForEntity(teamService + "/team/" + teamId, Team.class);
    }
}
