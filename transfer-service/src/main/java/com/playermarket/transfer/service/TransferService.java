package com.playermarket.transfer.service;

import com.playermarket.transfer.exception.ServiceException;
import com.playermarket.transfer.model.PlayerTransferResponse;
import com.playermarket.transfer.model.TransferRequest;
import com.playermarket.transfer.model.TransferResponse;

import java.util.List;

public interface TransferService {

    TransferResponse createTransfer(TransferRequest transferRequest) throws ServiceException;

    TransferResponse getTransferDetails(Long id) throws ServiceException;

    List<PlayerTransferResponse> getPlayerTeams(Long id) throws ServiceException;
}
