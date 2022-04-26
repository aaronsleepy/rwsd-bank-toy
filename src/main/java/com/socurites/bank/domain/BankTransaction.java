package com.socurites.bank.domain;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;
}
