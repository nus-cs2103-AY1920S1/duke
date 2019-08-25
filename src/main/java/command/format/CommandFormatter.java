package com.leeyiyuan.command.format;


import com.leeyiyuan.command.Command;

/** 
 * Represents a formatter for a specific command. 
 */
public abstract class CommandFormatter {

    /**
     * Returns a Command parsed from a string of text.
     *
     * @param text Text to parse Command from.
     * @return Command parsed from text.
     * @throws CommandParseException If Command cannot be parsed from text.
     */
    public abstract Command parse(String text) throws CommandParseException;
}
