package com.playermarket.player.mapper;

import com.playermarket.player.domain.Player;
import com.playermarket.player.model.PlayerRequest;
import com.playermarket.player.model.PlayerResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    Player playerRequestToPlayer(PlayerRequest playerRequest);

    PlayerResponse playerToPlayerResponse(Player player);
}
