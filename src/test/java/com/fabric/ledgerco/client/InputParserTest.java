package com.fabric.ledgerco.client;

import com.fabric.ledgerco.Loan;
import com.fabric.ledgerco.Payment;
import com.fabric.ledgerco.exception.InvalidPropertyException;
import org.junit.jupiter.api.Test;

import static com.fabric.ledgerco.Loan.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class InputParserTest {
    @Test
    void shouldParseAndReturnALoan() throws InvalidPropertyException {
        String input = "LOAN IDIDI Dale 10000 5 4";
        ICommand command = InputParser.parse(input);
        ICommandResult result = command.execute();
        Loan loan = (Loan) result;
        Loan expectedLoan = createLoan("IDIDI", "Dale", 10000, 5, 4);
        assertThat(loan, is(equalTo(expectedLoan)));
    }

    @Test
    void shouldParseAndReturnAPayment() throws InvalidPropertyException {
        String input = "PAYMENT IDIDI Dale 1000 5";
        ICommand command = InputParser.parse(input);
        ICommandResult result = command.execute();
        Payment payment = (Payment) result;
        Payment expectedLoan = new Payment("IDIDI", "Dale", 1000, 5);
        assertThat(payment, is(equalTo(expectedLoan)));
    }
}