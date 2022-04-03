package com.fabric.ledgerco.client;

import com.fabric.ledgerco.model.MarketPlace;
import com.fabric.ledgerco.exception.CommandNotSuppoertedException;

public class CommandFactory {
    private static final MarketPlace marketPlace = new MarketPlace();

    public static ICommand getCommand(String... args) throws CommandNotSuppoertedException {
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
