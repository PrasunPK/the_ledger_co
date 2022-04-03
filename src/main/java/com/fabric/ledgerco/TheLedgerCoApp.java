package com.fabric.ledgerco;

import com.fabric.ledgerco.client.ICommand;
import com.fabric.ledgerco.client.ICommandResult;
import com.fabric.ledgerco.client.InputParser;
import com.fabric.ledgerco.exception.CommandNotSuppoertedException;
import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.exception.LoanNotFoundException;
import com.fabric.ledgerco.model.MarketPlace;

import java.util.List;
import java.util.Objects;

public class TheLedgerCoApp {

    public static void main(String[] args) throws CommandNotSuppoertedException, InvalidPropertyException, LoanNotFoundException {
        String input1 = "LOAN IDIDI Dale 10000 5 4\n" +
                "LOAN MBI Harry 2000 2 2\n" +
                "BALANCE IDIDI Dale 5\n" +
                "BALANCE IDIDI Dale 40\n" +
                "BALANCE MBI Harry 12\n" +
                "BALANCE MBI Harry 0";

        String input2 = "LOAN IDIDI Dale 5000 1 6\n" +
                "LOAN MBI Harry 10000 3 7\n" +
                "LOAN UON Shelly 15000 2 9\n" +
                "PAYMENT IDIDI Dale 1000 5\n" +
                "PAYMENT MBI Harry 5000 10\n" +
                "PAYMENT UON Shelly 7000 12\n" +
                "BALANCE IDIDI Dale 3\n" +
                "BALANCE IDIDI Dale 6\n" +
                "BALANCE UON Shelly 12\n" +
                "BALANCE MBI Harry 12";

        System.out.println("\nOUTPUT:");
        process(input1);
        System.out.println("\nOUTPUT:");
        process(input2);
    }

    private static void process(String input1) throws CommandNotSuppoertedException, InvalidPropertyException, LoanNotFoundException {
        MarketPlace marketPlace = new MarketPlace();

        List<ICommand> commands = InputParser.parse(marketPlace, input1);

        for (ICommand command : commands) {
            ICommandResult result = command.execute();
            if (!Objects.equals(result.toString(), "")) {
                System.out.println(result);
            }
        }
    }
}
