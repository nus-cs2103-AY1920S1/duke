package com.leeyiyuan.command.format;


import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.TaskDoneCommand;
import java.util.regex.Pattern;

public class TaskDoneCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^done [0-9]+$", text)) {
            int index = Integer.parseInt(text.split(" ")[1]);
            return new TaskDoneCommand(index);
        } else {
            throw new UnsupportedCommandException("Unknown command.");
        }
    }
}
