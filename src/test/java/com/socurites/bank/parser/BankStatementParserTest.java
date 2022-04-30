package com.socurites.bank.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BankStatementParserTest {
    @Test
    public void shouldParseOneCorrection() {
        System.out.println("Test");
    }
}
