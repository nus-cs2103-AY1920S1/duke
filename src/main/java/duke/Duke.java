package duke;

import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "-".repeat(60);
    private static TaskList taskList = new TaskList();

    private static void echo(final String message) {
        System.out.println(separator);
        System.out.println(message.stripTrailing());
        System.out.println(separator);
        System.out.println();
    }

    private static void handleList() {
        echo("Here are the tasks in your list:\n" + taskList.toString());
    }

    private static void handleAddTask(final Task task) {
        if (taskList.addTask(task)) {
            echo("Got it. I've added this task: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list");
        } else {
            echo("Failed to add task :-(");
        }
    }

    private static void handleTodo(String arguments) {
        handleAddTask(new Todo(arguments));
    }

    private static void handleDeadline(String arguments) {
        String[] splitArgs = arguments.split("\\s+/by\\s+", 2);
        Deadline deadline = new Deadline(splitArgs[0], splitArgs[1]);
        handleAddTask(deadline);
    }

    private static void handleEvent(String arguments) {
        String[] splitArgs = arguments.split("\\s+/at\\s+", 2);
        Event event = new Event(splitArgs[0], splitArgs[1]);
        handleAddTask(event);
    }

    private static void handleDone(String arguments) {
        Task task = taskList.getTask(Integer.parseInt(arguments) - 1);
        task.markAsDone();
        echo("Nice! I've marked this task as done:\n  " + task);
    }

    public static void main(String[] args) {
        echo("Hello from\n" + logo + "What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            // split has either 1 or 2 elements
            String[] split = input.split("\\s+", 2);
            String command = split[0].toLowerCase();
            // If split.length == 1, there are no arguments
            String arguments = (split.length == 1) ? "" : split[1];
            switch (command) {
                case "list":
                    handleList();
                    break;
                case "bye":
                    echo("Bye. Hope to see you again soon!");
                    return;
                case "todo":
                    handleTodo(arguments);
                    break;
                case "deadline":
                    handleDeadline(arguments);
                    break;
                case "event":
                    handleEvent(arguments);
                    break;
                case "done":
                    handleDone(arguments);
                    break;
                default:
                    echo("Unrecognised command :-(");
                    break;
            }
        }
    }
}
