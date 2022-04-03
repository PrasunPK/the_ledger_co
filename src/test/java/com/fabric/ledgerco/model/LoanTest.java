package com.fabric.ledgerco.model;

import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.model.Balance;
import com.fabric.ledgerco.model.Loan;
import com.fabric.ledgerco.model.Payment;
import org.junit.jupiter.api.Test;

import static com.fabric.ledgerco.model.Loan.createLoan;
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


    @Test
    void shouldGetMonthlyEmiAmountInCeilling() throws InvalidPropertyException {
        Loan loan = createLoan("MBI", "Robert", 2000, 2, 2);
        Balance balance = loan.getRemainingAmount(12);

        Balance expectedBalance = new Balance("MBI", "Robert", 1044, 12);
        assertThat(balance, is(expectedBalance));
    }

    @Test
    void shouldGetRemainingAmountInCeillingAfterALumpSumAmountPaid() throws InvalidPropertyException {
        Loan loan = createLoan("MBI", "Robert", 5000, 1, 6);
        Payment payment = new Payment("MBI", "Robert", 1000, 5);
        loan.transact(payment);

        Balance balance = loan.getRemainingAmount(3);

        Balance expectedBalance = new Balance("MBI", "Robert", 1326, 9);
        assertThat(balance, is(expectedBalance));
    }

    @Test
    void shouldGetRemainingAmountAfterALumpSumAmountIsPaidInAdvance() throws InvalidPropertyException {
        Loan loan = createLoan("MBI", "Robert", 5000, 1, 6);
        Payment payment = new Payment("MBI", "Robert", 1000, 5);
        loan.transact(payment);

        Balance balance = loan.getRemainingAmount(6);

        Balance expectedBalance = new Balance("MBI", "Robert", 3652, 4);
        assertThat(balance, is(expectedBalance));
    }
}