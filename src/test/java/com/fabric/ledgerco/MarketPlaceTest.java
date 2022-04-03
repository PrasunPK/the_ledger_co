package com.fabric.ledgerco;

import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.exception.LoanNotFoundException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MarketPlaceTest {
    @Test
    void shouldReturnTheBalanceGivenBorrowerTookALoan() throws InvalidPropertyException, LoanNotFoundException {
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.createLoan("IDIDI", "Dale", 10000, 5, 4);

        Balance balance = marketPlace.balance("IDIDI", "Dale", 5);

        Balance expectedBalance = new Balance("IDIDI", "Dale", 1000, 55);

        assertThat(balance, is(expectedBalance));
    }

    @Test
    void shouldReturnTheBalanceGivenMultipleBorrowerTookLoanFromSameBank() throws InvalidPropertyException, LoanNotFoundException {
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.createLoan("IDIDI", "Dale", 10000, 5, 4);
        marketPlace.createLoan("IDIDI", "Robert", 5000, 5, 4);

        Balance balance = marketPlace.balance("IDIDI", "Dale", 5);

        Balance expectedBalance = new Balance("IDIDI", "Dale", 1000, 55);

        assertThat(balance, is(expectedBalance));
    }
}
