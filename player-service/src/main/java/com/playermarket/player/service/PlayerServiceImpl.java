package com.playermarket.player.service;

import com.playermarket.player.domain.Player;
import com.playermarket.player.model.PlayerOverview;
import com.playermarket.player.model.PlayerRequest;
import com.playermarket.player.model.PlayerResponse;
import com.playermarket.player.exception.ServiceException;
import com.playermarket.player.mapper.PlayerMapper;
import com.playermarket.player.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerMapper playerMapper;

    @Override
    public List<PlayerOverview> getPlayers() {
        List<Player> playerList = playerRepository.findAll();

        return playerMapper.playerListToRestModel(playerList);
    }

    @Override
    public PlayerResponse createPlayer(PlayerRequest playerRequest) {
        Player player = playerMapper.playerRequestToPlayer(playerRequest);
        player = playerRepository.save(player);

        return playerMapper.playerToPlayerResponse(player);
    }

    @Override
    public PlayerResponse getPlayerDetails(Long id) throws ServiceException {
        Optional<Player> player = playerRepository.findById(id);
        String message;

        if (player.isPresent()) {
            return playerMapper.playerToPlayerResponse(player.get());
        } else {
            message = "The player with ID = " + id + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }
    }

    @Override
    public PlayerResponse updatePlayer(Long id, PlayerRequest playerRequest) throws ServiceException {
        Optional<Player> player = playerRepository.findById(id);
        String message;

        if (!player.isPresent()) {
            message = "The player with ID = " + id + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }

        if (playerRequest.getTeamId() != null) {
            player.get().setTeamId(playerRequest.getTeamId());
        }
        if (playerRequest.getFirstName() != null) {
            player.get().setFirstName(playerRequest.getFirstName());
        }
        if (playerRequest.getLastName() != null) {
            player.get().setLastName(playerRequest.getLastName());
        }
        if (playerRequest.getBirthDate() != null) {
            player.get().setBirthDate(playerRequest.getBirthDate());
        }
        if (playerRequest.getCareerStart() != null) {
            player.get().setCareerStart(playerRequest.getCareerStart());
        }

        Player result = playerRepository.save(player.get());

        return playerMapper.playerToPlayerResponse(result);
    }

    @Override
    public PlayerResponse deletePlayer(Long id) throws ServiceException {
        Optional<Player> player = playerRepository.findById(id);
        String message;

        if (!player.isPresent()) {
            message = "The player with ID = " + id + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }

        playerRepository.delete(player.get());

        return playerMapper.playerToPlayerResponse(player.get());
    }
}
