package com.leeyiyuan.command.format;


import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.DeleteTaskCommand;
import java.util.regex.Pattern;

public class DeleteTaskCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^delete ?$", text)) {
            throw new CommandParseException("Index of delete command cannot be empty.");
        } else if (Pattern.matches("^delete [0-9]+$", text)) {
            int index = Integer.parseInt(text.split(" ")[1]);
            return new DeleteTaskCommand(index);
        } else {
            throw new CommandParseException("Unknown command.");
        }
    }
}
