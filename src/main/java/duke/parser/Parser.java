package duke.parser;

import duke.command.*;

public class Parser {

    /**
     * method that handles input, takes in a String and adds its allocated Command.
     * @param fullCommand String input from user.
     * @return Command based on the first word of String command.
     * @throws IllegalArgumentException if the command is not one of the valid ones.
     */
    public static Command parse(String fullCommand) throws IllegalArgumentException {
        String[] splitCommand = fullCommand.split(" ", 0);
        switch (splitCommand[0]) {
            case "t":
            case "d":
            case "e":
                return new AddCommand(fullCommand, splitCommand);
            case "done":
                return new DoneCommand(fullCommand, splitCommand);
            case "list":
                return new ListCommand(fullCommand, splitCommand);
            case "bye":
                return new ExitCommand(fullCommand, splitCommand);
            case "delete":
                return new DeleteCommand(fullCommand, splitCommand);
            case "find":
                return new FindCommand(fullCommand, splitCommand);
            case "help":
                return new HelpCommand(fullCommand, splitCommand);
            default:
                throw new IllegalArgumentException("Invalid Command");
        }
    }
}
