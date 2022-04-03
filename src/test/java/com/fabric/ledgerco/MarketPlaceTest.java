package com.fabric.ledgerco;

import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.exception.LoanNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MarketPlaceTest {
    private MarketPlace marketPlace;

    @BeforeEach
    void setupBeforeEach() {
        marketPlace = new MarketPlace();
    }

    @Test
    void shouldReturnTheBalanceGivenBorrowerTookALoan() throws InvalidPropertyException, LoanNotFoundException {
        String bank = "IDIDI";
        String borrower = "Dale";
        marketPlace.createLoan(bank, borrower, 10000, 5, 4);

        Balance balance = marketPlace.balance(bank, borrower, 5);

        Balance expectedBalance = new Balance(bank, borrower, 1000, 55);

        assertThat(balance, is(expectedBalance));
    }

    @Test
    void shouldReturnTheBalanceGivenMultipleBorrowerTookLoanFromSameBank() throws InvalidPropertyException, LoanNotFoundException {
        String bank = "IDIDI";
        String borrower = "Dale";
        String anotherBorrower = "Robert";

        marketPlace.createLoan(bank, borrower, 10000, 5, 4);
        marketPlace.createLoan(bank, anotherBorrower, 5000, 5, 4);

        Balance balance = marketPlace.balance(bank, borrower, 5);

        Balance expectedBalance = new Balance(bank, borrower, 1000, 55);

        assertThat(balance, is(expectedBalance));
    }

    @Test
    void shouldReturnTheBalanceGivenBorrowerTookALoanWhenQueriedMultipleTimes() throws InvalidPropertyException, LoanNotFoundException {
        String bank = "IDIDI";
        String borrower = "Dale";
        marketPlace.createLoan(bank, borrower, 10000, 5, 4);

        Balance balanceAfterFirstQuery = marketPlace.balance(bank, borrower, 5);
        Balance balanceAfterSecondQuery = marketPlace.balance(bank, borrower, 40);

        Balance expectedBalanceInFirstQuery = new Balance(bank, borrower, 1000, 55);
        Balance expectedBalanceInSecondQuery = new Balance(bank, borrower, 8000, 20);

        assertThat(balanceAfterFirstQuery, is(expectedBalanceInFirstQuery));
        assertThat(balanceAfterSecondQuery, is(expectedBalanceInSecondQuery));
    }

    @Test
    void shouldReturnTheBalanceAsZeroGivenBorrowerTookALoanWhenNoEmisLeft() throws InvalidPropertyException, LoanNotFoundException {
        String bank = "MBI";
        String borrower = "Robert";
        marketPlace.createLoan(bank, borrower, 2000, 2, 2);

        Balance balanceAfterFirstQuery = marketPlace.balance(bank, borrower, 12);
        Balance balanceAfterSecondQuery = marketPlace.balance(bank, borrower, 0);

        Balance expectedBalanceInFirstQuery = new Balance(bank, borrower, 1044, 12);
        Balance expectedBalanceInSecondQuery = new Balance(bank, borrower, 0, 24);

        assertThat(balanceAfterFirstQuery, is(expectedBalanceInFirstQuery));
        assertThat(balanceAfterSecondQuery, is(expectedBalanceInSecondQuery));
    }

    @Test
    void shouldMakeALumpSumPaymentAgainstALoanAndReturnRemainingAmountWhenBalanceIsQueriedMultipleTimes() throws InvalidPropertyException, LoanNotFoundException {
        String bank = "IDIDI";
        String borrower = "Dale";

        marketPlace.createLoan(bank, borrower, 5000, 1, 6);
        marketPlace.makePayment(bank, borrower, 1000, 5);


        Balance balance = marketPlace.balance(bank, borrower, 3);
        Balance expectedBalance = new Balance(bank, borrower, 1326, 9);
        assertThat(balance, is(equalTo(expectedBalance)));

        Balance balanceAfterSixEMIs = marketPlace.balance(bank, borrower, 6);
        Balance expectedBalanceAfterSixEMIs = new Balance(bank, borrower, 3652, 4);
        assertThat(balanceAfterSixEMIs, is(equalTo(expectedBalanceAfterSixEMIs)));
    }

    @Test
    void shouldMakeALumpSumPaymentAgainstALoanAndReturnRemainingAmountWhenBalanceIsQueriedOnce() throws InvalidPropertyException, LoanNotFoundException {
        String bank = "UBI";
        String borrower = "Kane";

        marketPlace.createLoan(bank, borrower, 15000, 2, 9);
        marketPlace.makePayment(bank, borrower, 7000, 12);


        Balance balance = marketPlace.balance(bank, borrower, 12);
        Balance expectedBalance = new Balance(bank, borrower, 15856, 3);
        assertThat(balance, is(equalTo(expectedBalance)));
    }
}
