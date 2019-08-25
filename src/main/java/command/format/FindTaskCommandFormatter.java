package com.leeyiyuan.command.format;


import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.FindTaskCommand;
import java.util.regex.Pattern;

public class FindTaskCommandFormatter extends CommandFormatter {

    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^find [^\\s]+$", text)) {
            String keyword = text.split(" ", 2)[1];
            return new FindTaskCommand(keyword);
        } else {
            throw new UnsupportedCommandException("Unknown command.");
        }
    }
}
