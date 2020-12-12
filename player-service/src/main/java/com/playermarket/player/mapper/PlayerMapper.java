package com.playermarket.player.mapper;

import com.playermarket.player.domain.Player;
import com.playermarket.player.model.PlayerOverview;
import com.playermarket.player.model.PlayerRequest;
import com.playermarket.player.model.PlayerResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    Player playerRequestToPlayer(PlayerRequest playerRequest);

    PlayerResponse playerToPlayerResponse(Player player);

    List<PlayerOverview> playerListToRestModel(List<Player> playerList);
}
