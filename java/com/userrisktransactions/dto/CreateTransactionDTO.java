package com.userrisktransactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Setter
@Getter
public class CreateTransactionDTO {
    private BigDecimal amount;
    private String type;
    private LocalDateTime timestamp;
}
