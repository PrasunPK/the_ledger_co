package com.fabric.ledgerco;

import com.fabric.ledgerco.exception.InvalidPropertyException;
import org.junit.jupiter.api.Test;

import static com.fabric.ledgerco.Loan.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoanTest {
    @Test
    void shouldCalculateLoanMonthlyEmiAmount() throws InvalidPropertyException {
        Loan loan = createLoan("MBI", "Robert", 10000, 5, 4);
        int monthlyEmiAmount = loan.getMonthlyEmiAmount();
        assertThat(monthlyEmiAmount, is(200));
    }

    @Test
    void shouldThrowInvalidPropertyExceptionWhenNoOfYearsIsZero() {
        assertThrows(InvalidPropertyException.class, () -> createLoan("MBI", "Robert", 10000, 0, 4));
    }

    @Test
    void shouldThrowInvalidPropertyExceptionWhenPrincipalIsZero() {
        assertThrows(InvalidPropertyException.class, () -> createLoan("MBI", "Robert", 0, 5, 4));
    }

    @Test
    void shouldGetRemainingLoanAmount() throws InvalidPropertyException {
        Loan loan = createLoan("MBI", "Robert", 10000, 5, 4);
        Balance balance = loan.getRemainingAmount(5);

        Balance expectedBalance = new Balance("MBI", "Robert", 1000, 55);
        assertThat(balance, is(expectedBalance));
    }
}