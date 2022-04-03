package com.fabric.ledgerco;

import com.fabric.ledgerco.client.ICommand;
import com.fabric.ledgerco.client.InputParser;
import com.fabric.ledgerco.exception.CommandNotSuppoertedException;
import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.exception.LoanNotFoundException;
import com.fabric.ledgerco.model.MarketPlace;

import java.util.List;

public class TheLedgerCoApp {
    public static void main(String[] args) throws CommandNotSuppoertedException, InvalidPropertyException, LoanNotFoundException {
        MarketPlace marketPlace = new MarketPlace();

        String input = "LOAN IDIDI Dale 10000 5 4\n" +
                "LOAN MBI Harry 2000 2 2\n" +
                "BALANCE IDIDI Dale 5\n" +
                "BALANCE IDIDI Dale 40\n" +
                "BALANCE MBI Harry 12\n" +
                "BALANCE MBI Harry 0";

        List<ICommand> commands = InputParser.parse(input);

        for (ICommand command : commands) {
            command.execute();
        }
    }
}
