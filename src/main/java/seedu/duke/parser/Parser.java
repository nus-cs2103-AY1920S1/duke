package seedu.duke.parser;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DoneCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.PriorityCommand;
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
        switch (parts[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(parts[1]);
        case "done":
            int taskNum = Integer.parseInt(parts[1]);
            return new DoneCommand(taskNum);
        case "delete":
            taskNum = Integer.parseInt(parts[1]);
            return new DeleteCommand(taskNum);
        case "todo":
            return new AddCommand(parts[0], parts[1]);
        case "deadline": case "event":
            return new AddCommand(parts[0], parts[1]);
        case "#1" : case "#2" : case "#3":
            return new PriorityCommand(parts[0], parts[1]);
        default:
            throw new DukeException("");
        }
    }
}
