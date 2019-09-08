package seedu.duke.parser;

import seedu.duke.commands.*;
import seedu.duke.exception.DukeException;

/**
 * Represents a command checker to verify if a command is valid. If valid, the corresponding Command will be
 * returned.
 */
public class Parser {

    /** Checks if a command is valid and execute command. If command is incomplete, throws
     * ArrayOutOfBoundException. If command is invalid, throws DukeException.
     * @param command Represents the command to be executedd.
     * @return Command Represents the command corresponding to user input.
     */
    public Command parse(String command) throws DukeException{
        String[] parts = command.split(" ", 2);
                if (parts[0].equals("bye")) {
                    return new ExitCommand();
                } else if (command.equals("list")) {
                    return new ListCommand();
                } else if (parts[0].equals("done")) {
                    int taskNum = Integer.parseInt(parts[1]);
                    return new DoneCommand(taskNum);
                } else if (parts[0].equals("delete")) {
                    int taskNum = Integer.parseInt(parts[1]);
                    return new DeleteCommand(taskNum);
                } else if (parts[0].equals("find")) {
                    return new FindCommand(parts[1]);
                } else if (parts[0].equals("todo")) {
                    return new AddCommand(parts[0], parts[1]);
                } else if (parts[0].equals("deadline")) {
                    return new AddCommand(parts[0], parts[1]);
                } else if (parts[0].equals("event")) {
                    return new AddCommand(parts[0], parts[1]);
                } else {
                    throw new DukeException("");
                }
    }
}
