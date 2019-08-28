package duke.core;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.helper.DukeException;

public class Parser {

    /**
     * Understands the user's input command from class Ui.
     * public method called by Duke.run() in the for loop as part of the Command Pattern.
     *
     * @param inputCommand String that is obtained from the scanner which is located in Ui.
     * @return Command which can be any one of the commands based on the first word of the user input parsed.
     * @throws DukeException which happens if the user input is not one of the predefined inputs.
     */
    public static Command parse(String inputCommand) throws DukeException {
        String[] inputsplit = inputCommand.split(" ", 2);
        if (inputsplit[0].equalsIgnoreCase("list")) {
            return new ListCommand(inputCommand);
        } else if (inputsplit[0].equalsIgnoreCase("done")) {
            return new DoneCommand(inputCommand);
        } else if (inputsplit[0].equalsIgnoreCase("todo") || inputsplit[0].equalsIgnoreCase("deadline") ||
                inputsplit[0].equalsIgnoreCase("event")) {
            return new AddCommand(inputCommand);
        } else if (inputsplit[0].equalsIgnoreCase("delete")){
            return new DeleteCommand(inputCommand);
        } else if (inputsplit[0].equalsIgnoreCase("bye")){
            return new ExitCommand(inputCommand);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
