public class Parser {
    public static String parse(TaskList tasks, String command) {
        // Determine the type of command from the first token
        switch (command.split(" ")[0]) {
        // "list": outputs all tasks in the TaskList in a formatted manner
        case "list":
            return tasks.toString();
        // "done" sets the status of the task with a given task ID (its position) to completed
        case "done":
            return Parser.parseDone(tasks, command.split(" ", 2)[1]);
        // "todo": creates a Todo task (no attached date/time)
        case "todo":
            return Parser.parseTodo(tasks, command.split(" ", 2)[1]);
        // "deadline": creates a Deadline task (to be completed by a specified date/time)
        case "deadline":
            return Parser.parseDeadline(tasks, command.split(" ", 2)[1]);
        // "event": creates a Event task (no attached date/time)
        case "event":
            return Parser.parseEvent(tasks, command.split(" ", 2)[1]);
        // CATCH: discard all unknown commands
        default:
            return "Unknown command!";
        }
    }

    public static String parseDone(TaskList tasks, String argString) {
        // Discards additional arguments apart from the first
        int id = Integer.valueOf(argString.split(" ")[0]);
        // Guard against invalid position numbers
        if (id < 1 || id > tasks.numberOfTasks()) {
            return "This task does not exist!";
        }
        return tasks.getTask(id - 1).complete();
    }

    public static String parseTodo(TaskList tasks, String argString) {
        // Entire argString is the description of the Todo task
        return tasks.addTask(new Todo(argString));
    }

    public static String parseDeadline(TaskList tasks, String argString) {
        // argString contains two arguments: description, byDeadline
        String[] args = argString.split(" /by ");
        // Guard against invalid position numbers
        if (args.length != 2) {
            return "Incorrect number of arguments!";
        }
        return tasks.addTask(new Deadline(args[0], args[1]));
    }

    public static String parseEvent(TaskList tasks, String argString) {
        // argString contains two arguments: description, atTime
        String[] args = argString.split(" /at ");
        // Guard against invalid position numbers
        if (args.length != 2) {
            return "Incorrect number of arguments!";
        }
        return tasks.addTask(new Event(args[0], args[1]));
    }
}
