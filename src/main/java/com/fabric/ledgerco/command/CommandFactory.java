package com.fabric.ledgerco.command;

import com.fabric.ledgerco.exception.CommandNotSuppoertedException;
import com.fabric.ledgerco.model.MarketPlace;

public class CommandFactory {

    public static ICommand getCommand(MarketPlace marketPlace, String... args) throws CommandNotSuppoertedException {
        if (args[0].equals("LOAN")) {
            return new LoanCommand(marketPlace, args[1], args[2], args[3], args[4], args[5]);
        }
        if (args[0].equals("PAYMENT")) {
            return new PaymentCommand(marketPlace, args[1], args[2], args[3], args[4]);
        }
        if (args[0].equals("BALANCE")) {
            return new BalanceCommand(marketPlace, args[1], args[2], args[3]);
        }
        throw new CommandNotSuppoertedException();
    }
}
