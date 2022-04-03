package com.fabric.ledgerco;

import com.fabric.ledgerco.client.ICommand;
import com.fabric.ledgerco.client.ICommandResult;
import com.fabric.ledgerco.client.InputParser;
import com.fabric.ledgerco.exception.CommandNotSuppoertedException;
import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.exception.LoanNotFoundException;
import com.fabric.ledgerco.model.MarketPlace;
import com.fabric.ledgerco.util.FileOperation;

import java.util.List;
import java.util.Objects;

public class TheLedgerCoApp {

    private static final String EMPTY_STRING = "";

    public static void main(String[] args) {
        String firstInput = FileOperation.readFile("./src/main/resources/sample_input_1.txt");
        String secondInput = FileOperation.readFile("./src/main/resources/sample_input_2.txt");

        System.out.println("\nOUTPUT:");
        process(firstInput);

        System.out.println("\nOUTPUT:");
        process(secondInput);
    }

    private static void process(String input1) {
        MarketPlace marketPlace = new MarketPlace();
        try {
            List<ICommand> commands = InputParser.parse(marketPlace, input1);

            for (ICommand command : commands) {
                ICommandResult result = command.execute();
                if (!Objects.equals(result.toString(), EMPTY_STRING)) {
                    System.out.println(result);
                }
            }
        } catch (Exception | CommandNotSuppoertedException | InvalidPropertyException | LoanNotFoundException e) {
            e.printStackTrace();
        }
    }
}
