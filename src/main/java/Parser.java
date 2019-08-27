import java.io.IOException;
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

    public static String parse(String command, TaskList tasks, Storage fileMgr) {
        try {
            // Catch empty commands (ENTER key pressed with no other input)
            if (command.equals("")) {
                throw new DukeNoCommandException();
            }
            // Determine the type of command from the first token
            switch (command.split(" ")[0]) {
            // "list": outputs all tasks in the TaskList in a formatted manner
            case "list":
                return tasks.toString();
            // "done" sets the status of the task with a given task ID (its position) to completed
            case "done":
                return Parser.parseDone(command, tasks, fileMgr);
            // "delete" removes a given task by its task ID from the TaskList
            case "delete":
                return Parser.parseDelete(command, tasks, fileMgr);
            // "todo": creates a Todo task (no attached date/time)
            case "todo":
                return Parser.parseTodo(command, tasks, fileMgr);
            // "deadline": creates a Deadline task (to be completed by a specified date/time)
            case "deadline":
                return Parser.parseDeadline(command, tasks, fileMgr);
            // "event": creates a Event task (no attached date/time)
            case "event":
                return Parser.parseEvent(command, tasks, fileMgr);
            // GUARD: against all unknown commands
            default:
                throw new DukeUnknownCommandException(command);
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public static String parseDone(String command, TaskList tasks, Storage fileMgr) throws DukeException {
        String[] tokens = command.split(" ");
        // GUARD: against too few (e.g. done) or too many (e.g. done 5 example) arguments
        if (tokens.length != 2) {
            throw new DukeIncorrectArgumentsException(1, DONE_TEMPLATE, tokens.length - 1, command);
        }

        try {
            // Attempt to parse the id of the task as an integer
            int id = Integer.valueOf(tokens[1]);
            // GUARD: against invalid (non-existent) task IDs
            if (id < 1 || id > tasks.numberOfTasks()) {
                throw new DukeInvalidTaskException(id, command);
            }
            Task task = tasks.getTask(id);
            task.complete();
            fileMgr.writeTaskList(tasks);
            return String.format("Nice! I've marked this task as done:\n  %s", task.toString());
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("id", "int", command);
        } catch (IOException e) {
            throw new DukeWriteToFileException("parseDone static method of Parser class");
        }
    }

    public static String parseDelete(String command, TaskList tasks, Storage fileMgr) throws DukeException {
        String[] tokens = command.split(" ");
        // GUARD: against too few (e.g. done) or too many (e.g. done 5 example) arguments
        if (tokens.length != 2) {
            throw new DukeIncorrectArgumentsException(1, DELETE_TEMPLATE, tokens.length - 1, command);
        }

        try {
            // Attempt to parse the id of the task as an integer
            int id = Integer.valueOf(tokens[1]);
            // GUARD: against invalid (non-existent) task IDs
            if (id < 1 || id > tasks.numberOfTasks()) {
                throw new DukeInvalidTaskException(id, command);
            }
            Task task = tasks.deleteTask(id);
            fileMgr.writeTaskList(tasks);
            String template = "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.";
            return String.format(template, task.toString(), tasks.numberOfTasks());
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("id", "int", command);
        } catch (IOException e) {
            throw new DukeWriteToFileException("parseDelete static method of Parser class");
        }
    }

    public static String parseTodo(String command, TaskList tasks, Storage fileMgr) throws DukeException {
        // GUARD: against empty todo description
        // If the 'todo' command is input with no arguments, trim() removes the trailing spaces
        if (command.equals("todo")) {
            throw new DukeIncorrectArgumentsException(1, TODO_TEMPLATE, 0, command);
        }

        // Otherwise entire argString is the description of the Todo task
        String argString = command.split(" ", 2)[1];
        Task newTodo = new TodoTask(argString);
        return Parser.parseAddTask(newTodo, tasks, fileMgr);
    }

    public static String parseDeadline(String command, TaskList tasks, Storage fileMgr) throws DukeException {
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
            Task newDeadline = new DeadlineTask(args[0], eventTime);
            return Parser.parseAddTask(newDeadline, tasks, fileMgr);
        } catch (ParseException e) {
            throw new DukeInvalidArgumentException("date | time", "Date", command);
        }
    }

    public static String parseEvent(String command, TaskList tasks, Storage fileMgr) throws DukeException {
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
            Task newEvent = new EventTask(args[0], eventTime);
            return Parser.parseAddTask(newEvent, tasks, fileMgr);
        } catch (ParseException e) {
            throw new DukeInvalidArgumentException("date | time", "Date", command);
        }
    }

    public static String parseAddTask(Task task, TaskList tasks, Storage fileMgr) throws DukeException {
        tasks.addTask(task);
        try {
            fileMgr.writeTaskList(tasks);
        } catch (IOException e) {
            throw new DukeWriteToFileException("parseAddTask static method of Parser class");
        } 
        String template = "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.";
        return String.format(template, task.toString(), tasks.numberOfTasks());
    }
}
