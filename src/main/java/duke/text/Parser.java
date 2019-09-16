package duke.text;

import duke.command.Command;
import duke.exception.DukeException;
import duke.types.CommandWord;

public class Parser {
    /**
     * Parse input string fullCommand, checks for invalid input format, and return a validated command.
     *
     * @param fullCommand Raw input string
     * @return Initialized Command instance
     * @throws DukeException If input string is of an invalid format.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.split(" ", 2);
        String commandWordString = inputs.length >= 1 ? inputs[0].toUpperCase() : "";
        String commandContent    = inputs.length == 2 ? inputs[1]               : "";

        try {
            Command command = CommandWord.valueOf(commandWordString).parseCommand(commandContent);
            assert command != null : "Command not created";
            return command;
        } catch (IllegalArgumentException ex) {
            throw new DukeException("OOPS!!! No such command");
        }
    }
}
