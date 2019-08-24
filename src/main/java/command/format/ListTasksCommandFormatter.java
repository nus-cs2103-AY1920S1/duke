package com.leeyiyuan.command.format;

import java.util.regex.Pattern;

import com.leeyiyuan.command.ListTasksCommand;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.format.CommandFormatter;
import com.leeyiyuan.command.format.CommandParseException;

public class ListTasksCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^list$", text)) {
            return new ListTasksCommand();
        } else {
            throw new CommandParseException("Unknown command.");
        }
    }

}
