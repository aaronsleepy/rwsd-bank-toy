package com.socurites.bank.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;
}
