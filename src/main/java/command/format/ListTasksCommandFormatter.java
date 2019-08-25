package com.leeyiyuan.command.format;


import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.ListTasksCommand;
import java.util.regex.Pattern;

public class ListTasksCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^list$", text)) {
            return new ListTasksCommand();
        } else {
            throw new UnsupportedCommandException("Unknown command.");
        }
    }
}
