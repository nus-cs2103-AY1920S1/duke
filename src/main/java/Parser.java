public class Parser {
    // Command templates
    public static final String DONE_TEMPLATE = "done <id>";
    public static final String DELETE_TEMPLATE = "delete <id>";
    public static final String TODO_TEMPLATE = "todo <description>";
    public static final String DEADLINE_TEMPLATE = "deadline <description> /by <date | time>";
    public static final String EVENT_TEMPLATE = "event <description> /by <date | time>";

    public static String parse(TaskList tasks, String command) throws DukeException {
        // Determine the type of command from the first token
        switch (command.split(" ")[0]) {
        // "list": outputs all tasks in the TaskList in a formatted manner
        case "list":
            return tasks.toString();
        // "done" sets the status of the task with a given task ID (its position) to completed
        case "done":
            return Parser.parseDone(tasks, command);
        // "delete" removes a given task by its task ID from the TaskList
        case "delete":
            return Parser.parseDelete(tasks, command);
        // "todo": creates a Todo task (no attached date/time)
        case "todo":
            return Parser.parseTodo(tasks, command);
        // "deadline": creates a Deadline task (to be completed by a specified date/time)
        case "deadline":
            return Parser.parseDeadline(tasks, command);
        // "event": creates a Event task (no attached date/time)
        case "event":
            return Parser.parseEvent(tasks, command);
        // GUARD: against all unknown commands
        default:
            throw new DukeUnknownCommandException(command);
        }
    }

    public static String parseDone(TaskList tasks, String command) throws DukeException {
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
            return tasks.getTask(id - 1).complete();
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("id", "int", command);
        }
    }

    public static String parseDelete(TaskList tasks, String command) throws DukeException {
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
            return tasks.deleteTask(id - 1);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("id", "int", command);
        }
    }

    public static String parseTodo(TaskList tasks, String command) throws DukeException {
        // GUARD: against empty todo description
        // If the 'todo' command is input with no arguments, trim() removes the trailing spaces
        if (command.equals("todo")) {
            throw new DukeIncorrectArgumentsException(1, TODO_TEMPLATE, 0, command);
        }

        // Otherwise entire argString is the description of the Todo task
        String argString = command.split(" ", 2)[1];
        return tasks.addTask(new TodoTask(argString));
    }

    public static String parseDeadline(TaskList tasks, String command) throws DukeException {
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
        return tasks.addTask(new DeadlineTask(args[0], args[1]));
    }

    public static String parseEvent(TaskList tasks, String command) throws DukeException {
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
        return tasks.addTask(new EventTask(args[0], args[1]));
    }
}
