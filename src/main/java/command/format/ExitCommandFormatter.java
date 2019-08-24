package com.leeyiyuan.command.format;


import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.ExitCommand;
import java.util.regex.Pattern;

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
