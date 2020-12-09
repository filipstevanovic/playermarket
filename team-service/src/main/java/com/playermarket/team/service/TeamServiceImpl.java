package com.playermarket.team.service;

import com.playermarket.team.domain.Team;
import com.playermarket.team.exception.ServiceException;
import com.playermarket.team.mapper.TeamMapper;
import com.playermarket.team.model.TeamRequest;
import com.playermarket.team.model.TeamResponse;
import com.playermarket.team.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamMapper teamMapper;

    @Override
    public TeamResponse createTeam(TeamRequest teamRequest) {
        Team team = teamMapper.teamRequestToTeam(teamRequest);
        team = teamRepository.save(team);

        return teamMapper.teamToTeamResponse(team);
    }

    @Override
    public TeamResponse getTeamDetails(Long id) throws ServiceException {
        Optional<Team> team = teamRepository.findById(id);
        String message;

        if (!team.isPresent()) {
            message = "The team with ID = " + id + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }

        return teamMapper.teamToTeamResponse(team.get());
    }

    @Override
    public TeamResponse updateTeam(Long id, TeamRequest teamRequest) throws ServiceException {
        Optional<Team> team = teamRepository.findById(id);
        String message;

        if (!team.isPresent()) {
            message = "The team with ID = " + id + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }

        if (teamRequest.getName() != null) {
            team.get().setName(teamRequest.getName());
        }
        if (teamRequest.getTeamCommissionPercentage() != null) {
            team.get().setTeamCommissionPercentage(teamRequest.getTeamCommissionPercentage());
        }

        Team result = teamRepository.save(team.get());

        return teamMapper.teamToTeamResponse(result);
    }

    @Override
    public TeamResponse deleteTeam(Long id) throws ServiceException {
        Optional<Team> team = teamRepository.findById(id);
        String message;

        if (!team.isPresent()) {
            message = "The team with ID = " + id + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }

        teamRepository.delete(team.get());

        return teamMapper.teamToTeamResponse(team.get());
    }

}
