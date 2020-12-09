package com.playermarket.transfer.service;

import com.playermarket.transfer.exception.ServiceException;
import com.playermarket.transfer.model.TransferRequest;
import com.playermarket.transfer.model.TransferResponse;

public interface TransferService {

    TransferResponse createTransfer(TransferRequest transferRequest);

    TransferResponse getTransferDetails(Long id) throws ServiceException;
}
