package duke.parser;

import duke.command.*;

import java.text.SimpleDateFormat;

public class Parser {

    /**
     * method that handles input, takes in a String and adds its allocated Command.
     * @param fullCommand String input from user.
     * @return Command based on the first word of String command.
     * @throws IllegalArgumentException if the command is not one of the valid ones.
     */
    public static Command parse(String fullCommand, SimpleDateFormat formatter) throws IllegalArgumentException {
        assert !fullCommand.equals("");
        String[] splitCommand = fullCommand.split(" ", 0);
        switch (splitCommand[0]) {
            case "t":
            case "d":
            case "e":
                return new AddCommand(fullCommand, splitCommand, formatter);
            case "done":
                return new DoneCommand(fullCommand, splitCommand, formatter);
            case "list":
                return new ListCommand(fullCommand, splitCommand, formatter);
            case "bye":
                return new ExitCommand(fullCommand, splitCommand, formatter);
            case "delete":
                return new DeleteCommand(fullCommand, splitCommand, formatter);
            case "find":
                return new FindCommand(fullCommand, splitCommand, formatter);
            case "help":
                return new HelpCommand(fullCommand, splitCommand, formatter);
            default:
                throw new IllegalArgumentException("Invalid Command");
        }
    }
}
