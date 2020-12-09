package com.playermarket.transfer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceException extends Exception {
    String message;
}
