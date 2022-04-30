package com.socurites.bank.parser;

import com.socurites.bank.domain.BankTransaction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BankStatementCSVParserTest {
    @Autowired
    private BankStatementCSVParser bankStatementParser;

    @Test
    public void shouldParseOneCorrection() {
        final String line = "30-01-2017,-50,Tesco";
        final BankTransaction result = bankStatementParser.parseFrom(line);

        final BankTransaction expected = BankTransaction.builder()
                .date(LocalDate.of(2017, Month.JANUARY, 30))
                .amount(-50)
                .description("Tesco")
                .build();
        final double tolerance = 0.0d;
        assertThat(expected.getDate()).isEqualTo(result.getDate());
        assertThat(expected.getAmount()).isEqualTo(result.getAmount(), offset(tolerance));
        assertThat(expected.getDescription()).isEqualTo(result.getDescription());
    }
}
