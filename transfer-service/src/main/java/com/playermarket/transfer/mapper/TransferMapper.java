package com.playermarket.transfer.mapper;

import com.playermarket.transfer.domain.Transfer;
import com.playermarket.transfer.model.PlayerTransferResponse;
import com.playermarket.transfer.model.TransferRequest;
import com.playermarket.transfer.model.TransferResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    Transfer transferRequestToTransfer(TransferRequest transferRequest);

    TransferResponse transferToTransferResponse(Transfer transfer);
}
