package duke.util;

import duke.command.Command;
import duke.command.AddTodoCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.SetDoneCommand;
import duke.command.ExitCommand;
import duke.command.FaultyCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;

/**
 * This class is responsible for parsing user input
 */
public class Parser {

    /**
     * Parses user input and returns a Command object accordingly
     * @param input User's input
     * @return appropriate Command object
     */
    public static Command parse(String input) {
        String[] command = input.split(" ", 2);
        switch (command[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            int idx = Integer.parseInt(command[1]) - 1;
            return new SetDoneCommand(idx);
        case "todo":
            try {
                return new AddTodoCommand(command[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new FaultyCommand(new DukeException("☹ OOPS!!! The description of a todo cannot be empty."));
            }
        case "deadline":
            try {
                String[] dl = command[1].split("/");
                return new AddDeadlineCommand(dl[0], dl[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new FaultyCommand(new DukeException("☹ OOPS!!! The description of a deadline cannot be empty."));
            }
        case "event":
            try {
                String[] ev = command[1].split("/");
                return new AddEventCommand(ev[0], ev[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new FaultyCommand(new DukeException("☹ OOPS!!! The description of an event cannot be empty."));
            }
        case "delete":
            try {
                int i = Integer.parseInt(command[1]) - 1;
                return new DeleteCommand(i);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return new FaultyCommand(new DukeException("☹ OOPS!!! Please specify which task to delete."));
            }
        case "find":
            try {
                return new FindCommand(command[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new FaultyCommand(new DukeException("☹ OOPS!!! The description of a todo cannot be empty."));
            }
        default:
            return new FaultyCommand(new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }
}
