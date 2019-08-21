import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private final static ArrayList<Task> ALL_TASKS = new ArrayList<>();

    public static void main(String[] args) {
        // Greet
        String greeting = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();

            // Exit
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {
                run(command);
            } catch (DukeException e) {
                System.out.println(e);
            }

            // Empty line separator at the end of the command response
            System.out.println();
        }

        scanner.close();
    }

    private static void run(String command) throws DukeException {
        if (command.equals("list")) {
            showTasks();
        } else if (command.matches("^done\\s+\\d+$")) {
            int taskId = Integer.parseInt(command.split("\\s+")[1]);
            completeTask(taskId);
        } else if (command.matches("^(todo|deadline|event).*")) {
            addTask(command);
        } else if (command.matches("^delete\\s+\\d+$")) {
            int taskId = Integer.parseInt(command.split("\\s+")[1]);
            deleteTask(taskId);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void showTasks() {
        if (ALL_TASKS.isEmpty()) {
            System.out.println("There are currently no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < ALL_TASKS.size(); i++) {
                Task task = ALL_TASKS.get(i);
                System.out.printf("%d.%s\n", (i + 1), task);
            }
        }
    }

    private static void completeTask(int taskId) throws DukeException {
        try {
            Task task = ALL_TASKS.get(taskId - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("  %s\n", task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    String.format("Task No.%d is not present in your list. Please enter a valid task ID.", taskId));
        }
    }

    private static void addTask(String command) throws DukeException {
        String[] commandArgs = command.split("\\s+", 2);
        String taskType = commandArgs[0];

        Task newTask;

        switch (taskType) {
        case "todo":
            if (commandArgs.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            newTask = new Todo(commandArgs[1]);
            break;
        case "deadline":
            if (commandArgs.length < 2 || commandArgs[1].equals("/by")) {
                throw new DukeException("The description and the due time of a deadline cannot be empty.");
            }
            if (commandArgs[1].startsWith("/by")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            if (!commandArgs[1].contains("/by") || commandArgs[1].endsWith("/by")) {
                throw new DukeException("The due time of a deadline cannot be empty.");
            }
            String[] deadlineArgs = commandArgs[1].split("\\s*/by\\s*", 2);
            newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            if (commandArgs.length < 2 || commandArgs[1].equals("/at")) {
                throw new DukeException("The description and the time of an event cannot be empty.");
            }
            if (commandArgs[1].startsWith("/at")) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            if (!commandArgs[1].contains("/at") || commandArgs[1].endsWith("/at")) {
                throw new DukeException("The time of an event cannot be empty.");
            }
            String[] eventArgs = commandArgs[1].split("\\s*/at\\s*", 2);
            newTask = new Event(eventArgs[0], eventArgs[1]);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        ALL_TASKS.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", newTask);
        int total = ALL_TASKS.size();
        System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }

    private static void deleteTask(int taskId) throws DukeException {
        try {
            Task task = ALL_TASKS.get(taskId - 1);
            ALL_TASKS.remove(task);
            System.out.println("Noted. I've removed this task:");
            System.out.printf("  %s\n", task);
            int total = ALL_TASKS.size();
            System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    String.format("Task No.%d is not present in your list. Please enter a valid task ID.", taskId));
        }
    }
}
