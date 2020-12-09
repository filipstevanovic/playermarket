package com.playermarket.team.controller;

import com.playermarket.team.exception.ExceptionHandler;
import com.playermarket.team.model.TeamRequest;
import com.playermarket.team.model.TeamResponse;
import com.playermarket.team.service.TeamService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Set of endpoints for teams REST service operations")
public class TeamApiImpl implements TeamApi {
    @Autowired
    TeamService teamService;

    @Override
    public ResponseEntity<TeamResponse> createTeam(TeamRequest teamRequest) {
        try {
            TeamResponse teamResponse = teamService.createTeam(teamRequest);
            return ResponseEntity.ok(teamResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }

    @Override
    public ResponseEntity<TeamResponse> getTeamDetails(Long id) {
        try {
            TeamResponse teamResponse = teamService.getTeamDetails(id);
            return ResponseEntity.ok(teamResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }

    @Override
    public ResponseEntity<TeamResponse> updateTeam(Long id, TeamRequest teamRequest) {
        try {
            TeamResponse teamResponse = teamService.updateTeam(id, teamRequest);
            return ResponseEntity.ok(teamResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }

    @Override
    public ResponseEntity<TeamResponse> deleteTeam(Long id) {
        try {
            TeamResponse teamResponse = teamService.deleteTeam(id);
            return ResponseEntity.ok(teamResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }
}
