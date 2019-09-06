package duke.ui;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Parser {
    /**
     * parses the command string and create according type of command.
     *
     * @param fullCommand string of command
     * @return return a new command
     * @throws DukeException when command is invalid
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ");
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (parts.length == 0) {
            throw new DukeException("Your input cannot be empty.");
        }

        switch (parts[0]) {
        case "list":
            return parseList(parts);
        case "done":
            return parseDone(parts);
        case "delete":
            return parseDelete(parts);
        case "todo":
            return parseTodo(parts, fullCommand);
        case "deadline":
            return parseDeadline(parts, fullCommand);
        case "event":
            return parseEvent(parts, fullCommand);
        case "find":
            return parseFind(parts, fullCommand);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseList(String[] parts) {
        return new ListCommand();
    }

    private static Command parseDone(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a done cannot be empty.");
        }
        int number = Integer.parseInt(parts[1]);
        return new DoneCommand(number);
    }

    private static Command parseDelete(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a delete cannot be empty.");
        }
        int number = Integer.parseInt(parts[1]);
        return new DeleteCommand(number);
    }

    private static Command parseTodo(String[] parts, String fullCommand) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = fullCommand.substring("todo".length() + 1);
        Task todoTask = new Todo(description);
        return new AddCommand(todoTask);
    }

    private static Command parseDeadline(String[] parts, String fullCommand) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        int index = fullCommand.indexOf("/");
        if (index == -1 || index + 4 >= fullCommand.length()) {
            throw new DukeException("Please provide a time for your deadline task");
        }
        if ("deadline".length() + 1 >= index - 1) {
            throw new DukeException("Please provide a proper name for your deadline task");
        }
        String description = fullCommand.substring("deadline".length() + 1, index - 1);
        String date = fullCommand.substring(index + 4);
        Task deadlineTask = new Deadline(description, date);
        return new AddCommand(deadlineTask);
    }

    private static Command parseEvent(String[] parts, String fullCommand) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        int index = fullCommand.indexOf("/");
        if (index == -1 || index + 4 >= fullCommand.length()) {
            throw new DukeException("Please provide a time for your event task");
        }
        if ("event".length() + 1 >= index - 1) {
            throw new DukeException("Please provide a proper name for your event task");
        }
        String description = fullCommand.substring("event".length() + 1, index - 1);
        String date = fullCommand.substring(index + 4);
        Task eventTask = new Event(description, date);
        return new AddCommand(eventTask);
    }

    private static Command parseFind(String[] parts, String fullCommand) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Please provide the string you want to search in the duke.tasks");
        }
        int index = fullCommand.indexOf(" ");
        String find = fullCommand.substring(index + 1);
        return new FindCommand(find);
    }
}
