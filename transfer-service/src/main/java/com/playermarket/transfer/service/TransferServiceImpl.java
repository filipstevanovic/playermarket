package com.playermarket.transfer.service;

import com.playermarket.transfer.adapter.player.PlayerAdapter;
import com.playermarket.transfer.adapter.team.TeamAdapter;
import com.playermarket.transfer.domain.Player;
import com.playermarket.transfer.domain.Team;
import com.playermarket.transfer.domain.Transfer;
import com.playermarket.transfer.model.PlayerTransferResponse;
import com.playermarket.transfer.model.TransferRequest;
import com.playermarket.transfer.model.TransferResponse;
import com.playermarket.transfer.exception.ServiceException;
import com.playermarket.transfer.mapper.TransferMapper;
import com.playermarket.transfer.repository.TransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService {
    @Autowired
    TransferRepository transferRepository;

    @Autowired
    TransferMapper transferMapper;

    @Autowired
    PlayerAdapter playerAdapter;

    @Autowired
    TeamAdapter teamAdapter;

    @Override
    public TransferResponse createTransfer(TransferRequest transferRequest) throws ServiceException {
        Transfer transfer = transferMapper.transferRequestToTransfer(transferRequest);
        transfer.setTransferFee(calculateFee(transferRequest));

        log.info("Saving transfer");
        playerAdapter.updatePlayer(transferRequest.getPlayerId(), transferRequest.getTeamIdTo());
        transfer = transferRepository.save(transfer);

        return transferMapper.transferToTransferResponse(transfer);
    }

    @Override
    public TransferResponse getTransferDetails(Long id) throws ServiceException {
        Optional<Transfer> transfer = transferRepository.findById(id);
        String message;

        if (transfer.isPresent()) {
            log.info("Transfer found, processing");
            return transferMapper.transferToTransferResponse(transfer.get());
        } else {
            message = "The transfer with ID = " + id + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }
    }

    @Override
    public List<PlayerTransferResponse> getPlayerTeams(Long id) throws ServiceException {
        List<Transfer> transferList = transferRepository.findAllByPlayerIdOrderByIdDesc(id);
        List<PlayerTransferResponse> responseList = new ArrayList<>();
        PlayerTransferResponse response = null;
        String message;

        for (Transfer t : transferList) {
            ResponseEntity<Team> team = teamAdapter.getTeam(t.getTeamIdTo());

            if (team == null || team.getBody() == null) {
                message = "The team with ID = " + t.getTeamIdTo() + " does not exist";
                log.error(message);
                throw new ServiceException(message);
            }

            response = new PlayerTransferResponse();
            response.setTeamId(t.getTeamIdTo());
            response.setTeamName(team.getBody().getName());
            response.transferDate(t.getTransferDate());
            responseList.add(response);
        }

        return responseList;
    }

    private BigDecimal calculateFee(TransferRequest transferRequest) throws ServiceException {
        ResponseEntity<Player> player = playerAdapter.getPlayer(transferRequest.getPlayerId());
        final Integer feeMultiplier = 100000;
        String message;

        if (player == null || player.getBody() == null) {
            message = "The player with ID = " + transferRequest.getPlayerId() + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }

        LocalDate careerStart = player.getBody().getCareerStart();
        LocalDate birthday = player.getBody().getBirthDate();
        LocalDate transferDate = transferRequest.getTransferDate();

        if (transferRequest.getTransferDate() == null) {
            transferDate = LocalDate.now();
        }

        if (careerStart == null || birthday == null) {
            message = "Not enough data (career or/and birth date(s) missing)";
            log.error(message);
            throw new ServiceException(message);
        }

        Long age = ChronoUnit.YEARS.between(birthday, transferDate);
        Long experience = ChronoUnit.MONTHS.between(careerStart, transferDate);
        BigDecimal transferFee = BigDecimal.valueOf(experience * feeMultiplier).divide(BigDecimal.valueOf(age), 2,
                RoundingMode.HALF_UP);
        ResponseEntity<Team> team = teamAdapter.getTeam(player.getBody().getTeamId());

        log.info("Name: {} {}, age: {} years, experience: {} months", player.getBody().getFirstName(),
                player.getBody().getLastName(), age, experience);

        if (team == null || team.getBody() == null) {
            message = "The team with ID = " + player.getBody().getTeamId() + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }

        log.info("Calculating the total transfer fee");
        return transferFee.add(transferFee.multiply(team.getBody().getTeamCommissionPercentage())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
    }
}
