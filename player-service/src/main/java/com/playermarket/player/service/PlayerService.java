package com.playermarket.player.service;

import com.playermarket.player.exception.ServiceException;
import com.playermarket.player.model.PlayerOverview;
import com.playermarket.player.model.PlayerRequest;
import com.playermarket.player.model.PlayerResponse;

import java.util.List;

public interface PlayerService {

    List<PlayerOverview> getPlayers();

    PlayerResponse createPlayer(PlayerRequest playerRequest);

    PlayerResponse getPlayerDetails(Long id) throws ServiceException;

    PlayerResponse updatePlayer(Long id, PlayerRequest teamRequest) throws ServiceException;

    PlayerResponse deletePlayer(Long id) throws ServiceException;
}
