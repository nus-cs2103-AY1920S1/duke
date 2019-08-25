package com.leeyiyuan.command.format;


import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.ExitCommand;
import java.util.regex.Pattern;

/** 
 * Represents a formatter for an ExitCommand.
 */
public class ExitCommandFormatter extends CommandFormatter {

    /**
     * {@inheritDoc}
     */
    @Override
    public Command parse(String text) throws CommandParseException {
        if (Pattern.matches("^bye$", text)) {
            return new ExitCommand();
        } else {
            throw new UnsupportedCommandException("Unknown command.");
        }
    }
}
