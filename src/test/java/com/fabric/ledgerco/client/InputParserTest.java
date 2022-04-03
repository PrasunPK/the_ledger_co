package com.fabric.ledgerco.client;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InputParserTest {
    @Test
    void shouldParseAndReturnALoan() {
        String input = "LOAN IDIDI Dale 10000 5 4";

        final List<ICommand>[] commands = new List[]{new ArrayList<>()};
        assertDoesNotThrow(() -> {
            commands[0] = InputParser.parse(input);
        });

        List<ICommand> result = commands[0];
        assertThat(result.get(0), instanceOf(LoanCommand.class));
    }

    @Test
    void shouldParseAndReturnAPayment() {
        String input = "PAYMENT IDIDI Dale 1000 5";
        final List<ICommand>[] commands = new List[]{new ArrayList<>()};
        assertDoesNotThrow(() -> {
            commands[0] = InputParser.parse(input);
        });

        List<ICommand> result = commands[0];
        assertThat(result.get(0), instanceOf(PaymentCommand.class));
    }

    @Test
    void shouldParseAndReturnABalance() {
        String input = "BALANCE IDIDI Harry 12";
        final List<ICommand>[] commands = new List[]{new ArrayList<>()};
        assertDoesNotThrow(() -> {
            commands[0] = InputParser.parse(input);
        });

        List<ICommand> result = commands[0];
        assertThat(result.get(0), instanceOf(BalanceCommand.class));
    }

    @Test
    void shouldParseAndReturnMultipleCommands() {
        String input = "LOAN IDIDI Dale 10000 5 4\n" +
                "PAYMENT IDIDI Dale 1000 5\n" +
                "BALANCE IDIDI Harry 12";
        final List<ICommand>[] commands = new List[]{new ArrayList<>()};
        assertDoesNotThrow(() -> {
            commands[0] = InputParser.parse(input);
        });

        List<ICommand> result = commands[0];
        assertThat(result.get(0), instanceOf(LoanCommand.class));
        assertThat(result.get(1), instanceOf(PaymentCommand.class));
        assertThat(result.get(2), instanceOf(BalanceCommand.class));
    }
}