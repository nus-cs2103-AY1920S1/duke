public class Parser {
    public static String parse(TaskList tasks, String command) {
        // Determine the type of command from the first token
        switch (command.split(" ")[0]) {
        // LIST: outputs all tasks in the TaskList in a formatted manner
        case "list":
            return tasks.toString();
        // DONE: sets the status of the task with a given task ID (its position) to completed
        case "done":
            return Parser.parseDone(tasks, command.split(" ", 2)[1]);
        // Otherwise store the commands entered in the TaskList
        default:
            return tasks.addTask(command);
        }
    }

    public static String parseDone(TaskList tasks, String args) {
        // Discards additional arguments apart from the first
        int id = Integer.valueOf(args.split(" ")[0]);
        return tasks.getTask(id - 1).complete();
    }
}