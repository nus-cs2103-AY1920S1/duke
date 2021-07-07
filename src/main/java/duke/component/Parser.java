package duke.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.exception.DukeException;
import duke.exception.DukeIncorrectArgumentsException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeUnknownCommandException;
import duke.task.Task;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

/**
 * Contains methods that parse supplied command strings for the Duke application.
 */
public final class Parser {
    // Command templates
    private static final String DONE_TEMPLATE = "done <id>";
    private static final String DELETE_TEMPLATE = "delete <id>";
    private static final String FIND_TEMPLATE = "find <search_string> | #<tag_string>";
    private static final String TAG_TEMPLATE = "tag <id> <tag_string>";
    private static final String TODO_TEMPLATE = "todo <description>";
    private static final String DEADLINE_TEMPLATE = "deadline <description> /by <date time>";
    private static final String EVENT_TEMPLATE = "event <description> /by <date time>";

    // Date parser
    private static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Parses the given input command <code>String</code> and returns the corresponding <code>Command</code> if valid.
     * 
     * @param command the input user command as a <code>String</code>.
     * @return an executable <code>Command</code> representing the instruction.
     * @throws DukeException if an error occurred when attempting to parse the user input
     */
    public static Command parse(String command) throws DukeException {
        // Parse trivial commands here
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        default:
        }

        assert command.length() > 0 : "Attempting to parse an invalid empty command string!";

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
        // "find" returns all tasks whose description contains the search string
        case "find":
            return Parser.parseFind(command);
        // "tag" tags a given task with a string
        case "tag":
            return Parser.parseTag(command);
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

    private static Command parseDone(String command) throws DukeException {
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

    private static Command parseDelete(String command) throws DukeException {
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

    private static Command parseFind(String command) throws DukeException {
        // GUARD: against empty search string
        // If the 'find' command is input with no arguments, trim() removes the trailing spaces
        if (command.equals("find")) {
            throw new DukeIncorrectArgumentsException(1, FIND_TEMPLATE, 0, command);
        }

        // Otherwise entire argString is the search string of the Find command
        String searchString = command.split(" ", 2)[1];

        // If searchString does not begin with a #, then it is a search by description
        if (searchString.charAt(0) != '#') {
            return new FindCommand(command, searchString);
        }
        
        // Else it is a tag search (join keywords to form tag)
        String tagString = searchString.substring(1).trim().replace(" ", "-");
        return new FindCommand(command, String.format("#%s", tagString));
    }

    private static Command parseTag(String command) throws DukeException {
        String[] tokens = command.split(" ", 3);
        // GUARD: against too few (e.g. tag 1) arguments
        if (tokens.length < 3) {
            throw new DukeIncorrectArgumentsException(2, TAG_TEMPLATE, tokens.length - 1, command);
        }

        try {
            // GUARD: against non-integer task IDs (attempt to parse the id of the task as an int)
            int id = Integer.valueOf(tokens[1]);
            String tag = String.join("-", tokens[2].split(" "));
            return new TagCommand(command, id, tag);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("id", "int", command);
        }
    }

    private static Command parseTodo(String command) throws DukeException {
        // GUARD: against empty todo description
        // If the 'todo' command is input with no arguments, trim() removes the trailing spaces
        if (command.equals("todo")) {
            throw new DukeIncorrectArgumentsException(1, TODO_TEMPLATE, 0, command);
        }

        // Otherwise entire argument string is the description of the Todo task
        String todoDescription = command.split(" ", 2)[1];
        Task newTask = new TodoTask(todoDescription, new ArrayList<String>());
        return Parser.parseAdd(command, newTask);
    }

    private static Command parseDeadline(String command) throws DukeException {
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
            Date deadlineTime = Parser.DATE_PARSER.parse(args[1]);
            Task newTask = new DeadlineTask(args[0], deadlineTime, new ArrayList<String>());
            return Parser.parseAdd(command, newTask);
        } catch (ParseException e) {
            throw new DukeInvalidArgumentException("date | time", "Date", command);
        }
    }

    private static Command parseEvent(String command) throws DukeException {
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
            Task newTask = new EventTask(args[0], eventTime, new ArrayList<String>());
            return Parser.parseAdd(command, newTask);
        } catch (ParseException e) {
            throw new DukeInvalidArgumentException("date | time", "Date", command);
        }
    }

    private static Command parseAdd(String command, Task newTask) {
        return new AddCommand(command, newTask);
    }
}
