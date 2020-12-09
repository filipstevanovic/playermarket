package com.playermarket.transfer.controller;

import com.playermarket.transfer.exception.ExceptionHandler;
import com.playermarket.transfer.model.TransferRequest;
import com.playermarket.transfer.model.TransferResponse;
import com.playermarket.transfer.service.TransferService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Set of endpoints for transfers REST service operations")
public class TransferApiImpl implements TransferApi {
    @Autowired
    TransferService transferService;

    @Override
    public ResponseEntity<TransferResponse> createTransfer(TransferRequest transferRequest) {
        try {
            TransferResponse transferResponse = transferService.createTransfer(transferRequest);
            return ResponseEntity.ok(transferResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }

    @Override
    public ResponseEntity<TransferResponse> getTransferDetails(Long id) {
        try {
            TransferResponse transferResponse = transferService.getTransferDetails(id);
            return ResponseEntity.ok(transferResponse);
        } catch (Exception e) {
            return ExceptionHandler.getException(e);
        }
    }
}
