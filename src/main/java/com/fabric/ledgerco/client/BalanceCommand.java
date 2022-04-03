package com.fabric.ledgerco.client;

import com.fabric.ledgerco.model.MarketPlace;
import com.fabric.ledgerco.exception.LoanNotFoundException;

public class BalanceCommand implements ICommand {
    private MarketPlace marketPlace;
    private final String arg;
    private final String arg1;
    private final String arg2;

    public BalanceCommand(MarketPlace marketPlace, String arg, String arg1, String arg2) {
        this.marketPlace = marketPlace;
        this.arg = arg;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public ICommandResult execute() throws LoanNotFoundException {
        return marketPlace.balance(arg, arg1, Integer.parseInt(arg2));
    }


}
