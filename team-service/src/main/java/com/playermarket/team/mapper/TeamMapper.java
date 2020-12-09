package com.playermarket.team.mapper;

import com.playermarket.team.domain.Team;
import com.playermarket.team.model.TeamRequest;
import com.playermarket.team.model.TeamResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    Team teamRequestToTeam(TeamRequest teamRequest);

    TeamResponse teamToTeamResponse(Team team);
}
