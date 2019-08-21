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

    private static void handleAddTask(final Task task) throws DukeException {
        if (taskList.addTask(task)) {
            echo("Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list");
        } else {
            throw new DukeException("Error: Failed to add task");
        }
    }

    private static void handleTodo(final String arguments) throws DukeException {
        if (arguments.isBlank()) {
            throw new DukeException("Error: The description of a task cannot "
                + "be empty");
        }
        handleAddTask(new Todo(arguments));
    }

    private static void handleDeadline(final String arguments) throws DukeException {
        String[] splitArgs = arguments.split("\\s*/by\\s*", 2);
        if (splitArgs[0].isBlank()) {
            throw new DukeException("Error: The description of a task cannot "
                + "be empty");
        } else if (splitArgs.length == 1) {
            throw new DukeException("Error: Deadline cannot be empty");
        }
        Deadline deadline = new Deadline(splitArgs[0], splitArgs[1]);
        handleAddTask(deadline);
    }

    private static void handleEvent(final String arguments) throws DukeException {
        String[] splitArgs = arguments.split("\\s*/at\\s*", 2);
        if (splitArgs[0].isBlank()) {
            throw new DukeException("Error: The description of a task cannot "
                + "be empty");
        } else if (splitArgs.length == 1) {
            throw new DukeException("Error: Event time cannot be empty");
        }
        Event event = new Event(splitArgs[0], splitArgs[1]);
        handleAddTask(event);
    }

    private static void handleDone(final String arguments) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(arguments) - 1;
            Task task = taskList.getTask(taskNumber);
            task.markAsDone();
            echo("Nice! I've marked this task as done:\n  " + task);
        } catch (NumberFormatException e) {
            throw new DukeException("Error: Invalid task number format");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error: Task does not exist");
        }
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
            try {
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
                        throw new DukeException("Error: Unrecognised command");
                }
            } catch (DukeException e) {
                echo(e.getMessage());
            }
        }
    }
}
