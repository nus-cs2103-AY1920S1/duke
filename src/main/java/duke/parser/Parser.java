package duke.parser;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;

public class Parser {

    /**
     * method that handles input, takes in a String and adds its allocated Command.
     * @param fullCommand String input from user.
     * @return Command based on the first word of String command.
     * @throws IllegalArgumentException if the command is not one of the valid ones.
     */
    public static Command parse(String fullCommand) throws IllegalArgumentException {
        String[] splitCommand = fullCommand.split(" ", 0);
        if (splitCommand[0].equals("t") || splitCommand[0].equals("d")
                || splitCommand[0].equals("e")) {
            return new AddCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("done")) {
            return new DoneCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("list")) {
            return new ListCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("bye")) {
            return new ExitCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("delete")) {
            return new DeleteCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("find")) {
            return new FindCommand(fullCommand, splitCommand);
        } else {
            throw new IllegalArgumentException("Invalid Command");
        }
    }
}
