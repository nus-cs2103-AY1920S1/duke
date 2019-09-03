package duke.main;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {
    public Parser() {
    }

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
        } else if (command.equals("find")) {
            return parseFind(details);
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

    public FindCommand parseFind(String details) throws DukeException {
        if (details.isBlank()) {
            throw new DukeException("OOPS!!! Please add keyword after 'find'");
        }
        return new FindCommand(details);
    }
}
