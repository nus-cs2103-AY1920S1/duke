package duke.util;

import duke.command.FindCommand;
import duke.exception.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * Class that encapsulates various parsing methods to parse
 * input Strings and return the appropriate Command Objects.
 * @see duke.command.Command
 * @see DateTime
 */
public class Parser {

    /**
     * Returns a Command object that was parsed from the user input.
     *
     * @param input String containing full user input
     * @return Command objects.
     * @throws DukeException if a generic recoverable runtime error is detected
     * @see Command
     */
    public static Command parseForCommands(String input) throws DukeException {
        String[] parameters = input.split(" ", 2);
        String command = parameters[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "done":
            return Parser.parseDoneCommand(parameters);
        case "delete":
            return Parser.parseDeleteCommand(parameters);
        case "todo":
            return Parser.parseTodoCommand(parameters);
        case "event":
            return Parser.parseEventCommand(parameters);
        case "deadline":
            return Parser.parseDeadlineCommand(parameters);
        case "find":
            return Parser.parseFindCommand(parameters);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, "
                    + "but I don't know what that means :-(");
        }
    }

    private static FindCommand parseFindCommand(String[] parameters) throws DukeException {
        try {
            return new FindCommand(parameters[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! You need to give me a search word to look for!");
        }
    }

    // parses the arguments for remaining parameters to construct a DoneCommand object
    private static DoneCommand parseDoneCommand(String[] parameters) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(parameters[1]);
            return new DoneCommand(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I'm not psychic! You need"
                    + " to tell me the ID of the task\n"
                    + "you're done with!");
        } catch (NumberFormatException e) {
            throw new DukeException("You need to provide me "
                    + "with a valid task index!\n"
                    + "(That means integer numbers only!)");
        }
    }

    // parses the arguments for remaining parameters to construct a DeleteCommand object
    private static DeleteCommand parseDeleteCommand(String[] parameters) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(parameters[1]);
            return new DeleteCommand(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I'm not psychic! You need"
                    + " to tell me the ID of the task\n"
                    + "you want to delete!");
        } catch (NumberFormatException e) {
            throw new DukeException("You need to provide me "
                    + "with a valid task index!\n"
                    + "(That means integer numbers only!)");
        }
    }

    // parses the arguments for remaining parameters to construct a TodoCommand object
    private static TodoCommand parseTodoCommand(String[] parameters) throws DukeException {
        try {
            return new TodoCommand(parameters[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo task"
                    + " cannot be empty.");
        }
    }

    // parses the arguments for remaining parameters to construct a EventCommand object
    private static EventCommand parseEventCommand(String[] parameters) throws DukeException {
        try {
            String[] deadlineParams = parameters[1].split(" /at ", 2);
            DateTime at = DateTime.parseString(deadlineParams[1]);
            return new EventCommand(deadlineParams[0], at);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! You need to give me both description\nAND time"
                    + " to create an event task.");
        }
    }

    // parses the arguments for remaining parameters to construct a DeadlineCommand object
    private static DeadlineCommand parseDeadlineCommand(String[] parameters) throws DukeException {
        try {
            String[] deadlineParams = parameters[1].split(" /by ", 2);
            DateTime by = DateTime.parseString(deadlineParams[1]);
            return new DeadlineCommand(deadlineParams[0], by);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! You need to give me both description\nAND time"
                    + " to create a deadline task.");
        }
    }
}
