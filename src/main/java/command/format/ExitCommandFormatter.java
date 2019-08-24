package com.leeyiyuan.command.format;

import java.util.regex.Pattern;

import com.leeyiyuan.command.ExitCommand;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.format.CommandFormatter;
import com.leeyiyuan.command.format.CommandParseException;

public class ExitCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^bye$", text)) {
            return new ExitCommand();
        } else {
            throw new CommandParseException("Unknown command.");
        }
    }

}
