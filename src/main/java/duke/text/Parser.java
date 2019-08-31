package duke.text;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {

    /**
     * Parse input string fullCommand, checks for invalid input format, and return a validated command.
     *
     * @param fullCommand Raw input string
     * @return Initialized Command instance
     * @throws DukeException If input string is of an invalid format.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] fullCommandSplit = fullCommand.split(" ", 2);
        String commandWord = fullCommandSplit[0];
        Command command;
        int taskListIndex;

        switch (commandWord) {
        case "list":
            if (fullCommandSplit.length > 1) {
                throw new DukeException("No parameters expected");
            }

            command = new ListCommand();
            break;

        case "bye":
            if (fullCommandSplit.length > 1) {
                throw new DukeException("No parameters expected");
            }

            command = new ByeCommand();
            break;

        case "todo":
            command = new AddCommand(Todo.parse(fullCommandSplit[1]));
            break;

        case "deadline":
            command = new AddCommand(Deadline.parse(fullCommandSplit[1]));
            break;

        case "event":
            command = new AddCommand(Event.parse(fullCommandSplit[1]));
            break;

        case "done":
            if (fullCommandSplit.length < 2) {
                throw new DukeException("OOPS!!! The index of " + commandWord
                        + " operation cannot be empty.");
            }

            if (!fullCommandSplit[1].matches("(0|[1-9]\\d*)")) {
                throw new DukeException("OOPS!!! The index of " + commandWord
                        + " operation must be a positive integer.");
            }

            taskListIndex = Integer.parseInt(fullCommandSplit[1]);
            command = new DoneCommand(taskListIndex);
            break;

        case "delete":
            if (fullCommandSplit.length < 2) {
                throw new DukeException("OOPS!!! The index of " + commandWord + " operation cannot be empty.");
            }

            if (!fullCommandSplit[1].matches("(0|[1-9]\\d*)")) {
                throw new DukeException("OOPS!!! The index of " + commandWord
                        + " operation must be a positive integer.");
            }

            taskListIndex = Integer.parseInt(fullCommandSplit[1]);
            command = new DeleteCommand(taskListIndex);
            break;

        case "find":
            if (fullCommandSplit.length < 2) {
                throw new DukeException("OOPS!!! The object to find cannot be empty.");
            }

            command = new FindCommand(fullCommandSplit[1]);
            break;

        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
