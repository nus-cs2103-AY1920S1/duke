package com.leeyiyuan.command.format;

import java.util.regex.Pattern;

import com.leeyiyuan.command.AddTodoTaskCommand;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.format.CommandFormatter;
import com.leeyiyuan.command.format.CommandParseException;

public class AddTodoTaskCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^todo ?$", text)) {
            throw new CommandParseException("Title of todo cannot be empty.");
        } else if (Pattern.matches("^todo .+$", text)) {
            String title = text.split("todo ", 2)[1];
            return new AddTodoTaskCommand(title);
        } else {
            throw new CommandParseException("Unhandled command.");
        }
    }

}
