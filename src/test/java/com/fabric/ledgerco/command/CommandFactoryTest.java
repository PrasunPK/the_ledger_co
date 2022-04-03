package com.fabric.ledgerco.command;

import com.fabric.ledgerco.exception.CommandNotSupportedException;
import com.fabric.ledgerco.model.MarketPlace;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandFactoryTest {
    @Test
    void shouldReturnRightLoanCommand() throws CommandNotSupportedException {
        MarketPlace marketPlace = new MarketPlace();
        ICommand command = CommandFactory.getCommand(marketPlace, "LOAN", "bank", "borrower", "1000", "5", "5");
        Class<LoanCommand> loanCommandClass = LoanCommand.class;
        int parameterCount = loanCommandClass.getConstructors()[0].getParameterCount();
        assertThat(command, instanceOf(loanCommandClass));
        assertThat(parameterCount, is(equalTo(6)));
    }

    @Test
    void shouldReturnRightPaymentCommand() throws CommandNotSupportedException {
        MarketPlace marketPlace = new MarketPlace();
        ICommand command = CommandFactory.getCommand(marketPlace, "PAYMENT", "bank", "borrower", "1000", "5");
        Class<PaymentCommand> paymentCommandClass = PaymentCommand.class;
        int parameterCount = paymentCommandClass.getConstructors()[0].getParameterCount();
        assertThat(command, instanceOf(paymentCommandClass));
        assertThat(parameterCount, is(equalTo(5)));
    }

    @Test
    void shouldReturnRightBalanceCommand() throws CommandNotSupportedException {
        MarketPlace marketPlace = new MarketPlace();
        ICommand command = CommandFactory.getCommand(marketPlace, "BALANCE", "bank", "borrower", "5");
        Class<BalanceCommand> balanceCommandClass = BalanceCommand.class;
        int parameterCount = balanceCommandClass.getConstructors()[0].getParameterCount();
        assertThat(command, instanceOf(balanceCommandClass));
        assertThat(parameterCount, is(equalTo(4)));
    }

    @Test
    void shouldThrowExceptionWhenAnInvalidCommandIsAsked() throws CommandNotSupportedException {
        MarketPlace marketPlace = new MarketPlace();
        assertThrows(CommandNotSupportedException.class,
                () -> CommandFactory.getCommand(marketPlace, "RANDOM", "bank", "borrower", "5"));
    }
}