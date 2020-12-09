package com.playermarket.transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TRANSFER")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long playerId;
    Long teamIdFrom;
    Long teamIdTo;
    BigDecimal transferFee;
    LocalDate transactionDate;
}
