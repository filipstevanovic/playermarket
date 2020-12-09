package com.playermarket.transfer.service;

import com.playermarket.transfer.adapter.player.PlayerAdapter;
import com.playermarket.transfer.domain.Player;
import com.playermarket.transfer.domain.Transfer;
import com.playermarket.transfer.model.TransferRequest;
import com.playermarket.transfer.model.TransferResponse;
import com.playermarket.transfer.exception.ServiceException;
import com.playermarket.transfer.mapper.TransferMapper;
import com.playermarket.transfer.repository.TransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Override
    public TransferResponse createTransfer(TransferRequest transferRequest) {
        /** TODO: Improvement */
        playerAdapter.updatePlayer(transferRequest.getPlayerId(), transferRequest.getTeamIdTo());

        Transfer transfer = transferMapper.transferRequestToTransfer(transferRequest);
        transfer = transferRepository.save(transfer);

        return transferMapper.transferToTransferResponse(transfer);
    }

    @Override
    public TransferResponse getTransferDetails(Long id) throws ServiceException {
        Optional<Transfer> transfer = transferRepository.findById(id);
        String message;

        if (transfer.isPresent()) {
            return transferMapper.transferToTransferResponse(transfer.get());
        } else {
            message = "The transfer with ID = " + id + " does not exist";
            log.error(message);
            throw new ServiceException(message);
        }
    }
}
