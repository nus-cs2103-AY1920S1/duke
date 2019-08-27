import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    // Command templates
    private static final String DONE_TEMPLATE = "done <id>";
    private static final String DELETE_TEMPLATE = "delete <id>";
    private static final String TODO_TEMPLATE = "todo <description>";
    private static final String DEADLINE_TEMPLATE = "deadline <description> /by <date time>";
    private static final String EVENT_TEMPLATE = "event <description> /by <date time>";

    // Date parser
    private static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static Command parse(String command) throws DukeException {
        // Parse trivial commands here
        switch (command) {
        case "":
            // Catch empty commands (ENTER key pressed with no other input)
            throw new DukeNoCommandException();
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        default:
        }

        // Determine the type of command from the first token
        switch (command.split(" ")[0]) {
        // "list": outputs all tasks in the TaskList in a formatted manner
        case "list":
            return new ListCommand();
        // "done" sets the status of the task with a given task ID (its position) to completed
        case "done":
            return Parser.parseDone(command);
        // "delete" removes a given task by its task ID from the TaskList
        case "delete":
            return Parser.parseDelete(command);
        // "todo": creates a Todo task (no attached date/time)
        case "todo":
            return Parser.parseTodo(command);
        // "deadline": creates a Deadline task (to be completed by a specified date/time)
        case "deadline":
            return Parser.parseDeadline(command);
        // "event": creates a Event task (no attached date/time)
        case "event":
            return Parser.parseEvent(command);
        // GUARD: against all unknown commands
        default:
            throw new DukeUnknownCommandException(command);
        }
    }

    public static Command parseDone(String command) throws DukeException {
        String[] tokens = command.split(" ");
        // GUARD: against too few (e.g. done) or too many (e.g. done 5 example) arguments
        if (tokens.length != 2) {
            throw new DukeIncorrectArgumentsException(1, DONE_TEMPLATE, tokens.length - 1, command);
        }

        try {
            // GUARD: against non-integer task IDs (attempt to parse the id of the task as an int)
            int id = Integer.valueOf(tokens[1]);
            return new DoneCommand(command, id);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("id", "int", command);
        }
    }

    public static Command parseDelete(String command) throws DukeException {
        String[] tokens = command.split(" ");
        // GUARD: against too few (e.g. done) or too many (e.g. done 5 example) arguments
        if (tokens.length != 2) {
            throw new DukeIncorrectArgumentsException(1, DELETE_TEMPLATE, tokens.length - 1, command);
        }

        try {
            // GUARD: against non-integer task IDs (attempt to parse the id of the task as an int)
            int id = Integer.valueOf(tokens[1]);
            return new DeleteCommand(command, id);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("id", "int", command);
        }
    }

    public static Command parseTodo(String command) throws DukeException {
        // GUARD: against empty todo description
        // If the 'todo' command is input with no arguments, trim() removes the trailing spaces
        if (command.equals("todo")) {
            throw new DukeIncorrectArgumentsException(1, TODO_TEMPLATE, 0, command);
        }

        // Otherwise entire argString is the description of the Todo task
        String argString = command.split(" ", 2)[1];
        return new TodoCommand(command, argString);
    }

    public static Command parseDeadline(String command) throws DukeException {
        // GUARD: against empty deadline description
        // If the 'deadline' command is input with no arguments, trim() removes the trailing spaces
        if (command.equals("deadline")) {
            throw new DukeIncorrectArgumentsException(1, DEADLINE_TEMPLATE, 0, command);
        }

        String[] args = command.split(" ", 2)[1].split(" /by ");
        // GUARD: against incorrect number of arguments or missing ' /by '
        // args should contain two arguments: description, byDeadline
        if (args.length != 2) {
            throw new DukeIncorrectArgumentsException(2, DEADLINE_TEMPLATE, args.length, command);
        }

        // Attempt to parse the date to construct the Deadline
        try {
            Date eventTime = Parser.DATE_PARSER.parse(args[1]);
            return new DeadlineCommand(command, args[0], eventTime);
        } catch (ParseException e) {
            throw new DukeInvalidArgumentException("date | time", "Date", command);
        }
    }

    public static Command parseEvent(String command) throws DukeException {
        // GUARD: against empty event description
        // If the 'event' command is input with no arguments, trim() removes the trailing spaces
        if (command.equals("event")) {
            throw new DukeIncorrectArgumentsException(1, EVENT_TEMPLATE, 0, command);
        }

        String[] args = command.split(" ", 2)[1].split(" /at ");
        // GUARD: against incorrect number of arguments or missing ' /at '
        // args should contain two arguments: description, atTime
        if (args.length != 2) {
            throw new DukeIncorrectArgumentsException(2, EVENT_TEMPLATE, args.length, command);
        }

        // Attempt to parse the date to construct the Event
        try {
            Date eventTime = Parser.DATE_PARSER.parse(args[1]);
            return new EventCommand(command, args[0], eventTime);
        } catch (ParseException e) {
            throw new DukeInvalidArgumentException("date | time", "Date", command);
        }
    }
}
