package duke.main;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Deals with understanding the commands given to the bot by the user.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Returns a Command object that corresponds to the fullCommand argument given to the bot
     * by the user as a String.
     *
     * @param fullCommand the command given to the bot by the user
     * @return the corresponding Command object
     * @throws DukeException if fullCommand is not one of the recognised commands
     */
    public Command parse(String[] fullCommand) throws DukeException {
        String command = fullCommand[0];
        String details = fullCommand[1];
        if (command.equals("bye")) {
            return parseBye(details);
        } else if (command.equals("list")) {
            return parseList(details);
        } else if (command.equals("done")) {
            return parseDone(details);
        } else if (command.equals("delete")) {
            return parseDelete(details);
        } else if (command.equals("deadline")) {
            return parseDeadline(details);
        } else if (command.equals("event")) {
            return parseEvent(details);
        } else if (command.equals("todo")) {
            return parseTodo(details);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-()");
        }
    }

    public ByeCommand parseBye(String details) throws DukeException {
        if (!details.isBlank()) {
            throw new DukeException("OOPS!!! No input is needed after bye'");
        }
        return new ByeCommand();
    }

    public ListCommand parseList(String details) throws DukeException {
        if (!details.isBlank()) {
            throw new DukeException("OOPS!!! No input is needed after 'list'");
        }
        return new ListCommand();
    }

    public DoneCommand parseDone(String details) throws DukeException {
        try {
            int index = Integer.parseInt(details) - 1;
            return new DoneCommand(index);
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! Please input a valid number after 'done'");
        }
    }

    public DeleteCommand parseDelete(String details) throws DukeException {
        try {
            int index = Integer.parseInt(details) - 1;
            return new DeleteCommand(index);
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! Please input a valid number after 'delete'");
        }
    }

    public DeadlineCommand parseDeadline(String details) throws DukeException {
        try {
            String[] split = details.split(" /by ");
            String task = split[0];
            String time = split[1];
            if (task.isBlank() || time.isBlank()) {
                throw new Exception();
            }
            return new DeadlineCommand(task, time);
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! Please input in this format: 'deadline <task> /by <time>'");
        }
    }

    public EventCommand parseEvent(String details) throws DukeException {
        try {
            String[] split = details.split(" /at ");
            String task = split[0];
            String time = split[1];
            if (task.isBlank() || time.isBlank()) {
                throw new Exception();
            }
            return new EventCommand(task, time);
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! Please input in this format: 'event <task> /at <time>'");
        }
    }

    public TodoCommand parseTodo(String details) throws DukeException {
        if (details.isBlank()) {
            throw new DukeException("OOPS!!! Please add task description after 'todo'");
        }
        return new TodoCommand(details);
    }
}
