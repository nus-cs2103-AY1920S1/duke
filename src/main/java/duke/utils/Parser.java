package duke.utils;

import java.util.LinkedList;

/**
 * Represents the parser that will take a string
 * input and make sense of what command the user
 * is using.
 */
public class Parser {
    private String[] split;
    private String type;

    /**
     * Creates a parser object that parses the string
     * and get what type of command the user is issuing.
     *
     * @param s the string input by the user of Duke
     */
    public Parser(String s) {
        this.split = s.split(" ");
        this.type = split[0];
    }

    /**
     * Gets the type of command the user has input.
     *
     * @return string that says what type of command the
     *     user is issuing.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the description for Tasks related commands.
     *
     * @return LinkedList of that are in the command
     */
    public LinkedList<String> getDesc() {
        //String actual = "";
        LinkedList<String> copy = new LinkedList<>();
        for (int i = 1; i < split.length; i++) {
            copy.add(split[i]);
        }

        return copy;
            
    }

}