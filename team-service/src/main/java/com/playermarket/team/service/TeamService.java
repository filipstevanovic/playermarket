package com.playermarket.team.service;

import com.playermarket.team.exception.ServiceException;
import com.playermarket.team.model.TeamRequest;
import com.playermarket.team.model.TeamResponse;

public interface TeamService {

    TeamResponse createTeam(TeamRequest teamRequest);

    TeamResponse getTeamDetails(Long id) throws ServiceException;

    TeamResponse updateTeam(Long id, TeamRequest teamRequest) throws ServiceException;

    TeamResponse deleteTeam(Long id) throws ServiceException;
}
