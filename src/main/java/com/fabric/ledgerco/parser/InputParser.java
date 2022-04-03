package com.fabric.ledgerco.parser;

import com.fabric.ledgerco.command.CommandFactory;
import com.fabric.ledgerco.command.ICommand;
import com.fabric.ledgerco.exception.CommandNotSuppoertedException;
import com.fabric.ledgerco.model.MarketPlace;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static List<ICommand> parse(MarketPlace marketPlace, String input) throws CommandNotSuppoertedException {
        String[] lines = input.split(NEW_LINE);
        List<ICommand> commands = new ArrayList<>();

        for (String line : lines) {
            String[] args = line.split(SPACE);

            String command = args[0];
            String arg1 = args[1];
            String arg2 = args[2];
            String arg3 = args[3];

            String arg4 = args.length > 4 ? args[4] : null;
            String arg5 = args.length == 6 ? args[5] : null;

            commands.add(CommandFactory.getCommand(marketPlace, command, arg1, arg2, arg3, arg4, arg5));
        }

        return commands;
    }
}
