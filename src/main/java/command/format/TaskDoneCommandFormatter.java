package com.leeyiyuan.command.format;

import java.util.regex.Pattern;

import com.leeyiyuan.command.TaskDoneCommand;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.format.CommandFormatter;
import com.leeyiyuan.command.format.CommandParseException;

public class TaskDoneCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^done [0-9]+$", text)) {
            int index = Integer.parseInt(text.split(" ")[1]);
            return new TaskDoneCommand(index);
        } else {
            throw new CommandParseException("Unknown command.");
        }
    }

}
