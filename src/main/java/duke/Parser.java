package duke;

import duke.commands.Command;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;

public class Parser {
    private String getCommand(String input) {
        String[] tokens = input.split(" ", 2);
        return tokens[0].trim().toLowerCase();
    }

    private String getDetails(String input) throws DukeException {
        try {
            String[] tokens = input.split(" ", 2);
            return tokens[1].trim();
        } catch (Exception e) {
            throw new DukeException("Details of your command not found.");
        }
    }

    private Command parseDone(String input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(getDetails(input)) - 1;
            return new DoneCommand(taskIndex);
        } catch (Exception e) {
            throw new DukeException("Invalid task number!");
        }
    }

    private Command parseDelete(String input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(getDetails(input)) - 1;
            return new DeleteCommand(taskIndex);
        } catch (Exception e) {
            throw new DukeException("Invalid task number!");
        }
    }

    private Command parseTodo(String input) throws DukeException {
        String description = getDetails(input).trim();
        if (description.isBlank()) {
            throw new DukeException("The task description cannot be empty.");
        }
        return new TodoCommand(description);
    }

    private Command parseDeadline(String input) throws DukeException {
        String[] taskDetails = getDetails(input).split("/by");
        String description = taskDetails[0].trim();
        String by = taskDetails[1].trim();
        if (description.isBlank()) {
            throw new DukeException("The task description cannot be empty.");
        } else if (by.isBlank()) {
            throw new DukeException("The task deadline cannot be empty.");
        }
        return new DeadlineCommand(description, by);
    }

    private Command parseEvent(String input) throws DukeException {
        String[] taskDetails = getDetails(input).split("/at");
        String description = taskDetails[0].trim();
        String at = taskDetails[1].trim();
        if (description.isBlank()) {
            throw new DukeException("The task description cannot be empty.");
        } else if (at.isBlank()) {
            throw new DukeException("The task date/time cannot be empty.");
        }
        return new EventCommand(description, at);
    }

    private Command parseFind(String input) throws DukeException {
        String keyword = getDetails(input).trim();
        if (keyword.isBlank()) {
            throw new DukeException("The task keyword cannot be empty.");
        }
        return new FindCommand(keyword);
    }

    public Command parse(String input) throws DukeException {
        String command = this.getCommand(input);
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return parseDone(input);
        case "delete":
            return parseDelete(input);
        case "todo":
            return parseTodo(input);
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        case "find":
            return parseFind(input);
        default:
            throw new DukeException("Please enter a valid command!");
        }
    }
}
