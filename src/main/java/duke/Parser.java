package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.InsufficientDetailsException;
import duke.exception.InvalidCommandException;

/**
 * Represents a parser that reads and parses raw command strings.
 */
public class Parser {
    /**
     * Parses raw commands and returns a type of <code>Command</code> correspondingly.
     * @param rawCommandDetails Details of the command provided by user.
     * @return A type of <code>Command</code> that corresponds to the raw command.
     * @throws DukeException If the raw commands are invalid, in the wrong format or are lacking required information.
     */
    static Command parse(String rawCommandDetails) throws DukeException {
        String commandDetails = rawCommandDetails.replaceAll("\\s+", " ");
        String command = commandDetails.split(" ")[0];
        switch (command) {
        case "bye":
            return bye();
        case "list":
            return list();
        case "delete":
            return delete(commandDetails);
        case "done":
            return done(commandDetails);
        case "find":
            return find(commandDetails);
        case "todo":
            return todo(commandDetails);
        case "deadline":
            return deadline(commandDetails);
        case "event":
            return event(commandDetails);
        default:
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static ExitCommand bye() {
        return new ExitCommand();
    }

    private static ListCommand list() {
        return new ListCommand();
    }

    private static Command delete(String commandDetails) throws InsufficientDetailsException {
        String[] commandSplit = commandDetails.split(" ");
        if (commandSplit.length < 2) {
            throw new InsufficientDetailsException("☹ OOPS!!! Please specify a task to delete.");
        }
        int index = Integer.parseInt(commandSplit[1]);
        return new DeleteCommand(index);
    }

    private static Command done(String commandDetails) throws InsufficientDetailsException {
        String[] commandSplit = commandDetails.split(" ");
        if (commandSplit.length < 2) {
            throw new InsufficientDetailsException("☹ OOPS!!! Please specify the task that has been done.");
        }
        int index = Integer.parseInt(commandSplit[1]);
        return new DoneCommand(index);
    }

    private static Command find(String commandDetails) throws InsufficientDetailsException {
        String[] commandSplit = commandDetails.split(" ");
        if (commandSplit.length < 2) {
            throw new InsufficientDetailsException("☹ OOPS!!! Please specify what you want to find.");
        }
        return new FindCommand(commandSplit[1].trim());
    }

    private static Command todo(String commandDetails) throws InsufficientDetailsException {
        String taskDetailsString;
        taskDetailsString = commandDetails.replaceFirst("todo", "").trim();
        if (taskDetailsString.length() == 0) {
            throw new InsufficientDetailsException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new AddTodoCommand(taskDetailsString);
        }
    }

    private static Command deadline(String commandDetails) {
        String taskDetailsString;
        taskDetailsString = commandDetails.replaceFirst("deadline", "").trim();
        return new AddDeadlineCommand(taskDetailsString);
    }

    private static Command event(String commandDetails) {
        String taskDetailsString;
        taskDetailsString = commandDetails.replaceFirst("event", "").trim();
        return new AddEventCommand(taskDetailsString);
    }
}
