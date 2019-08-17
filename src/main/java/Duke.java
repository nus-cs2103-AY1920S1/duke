import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while (scanner.hasNext()) {
            String command = scanner.nextLine();

            try {
                if (command.isEmpty()) {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                } else if (command.equals("bye")) {
                    printExitMessage();
                    break;
                } else if (command.equals("list")) {
                    printTasks();
                } else if (command.startsWith("done")) {
                    handleTaskDoneCommand(command);
                } else {
                    handleAddTaskCommand(command);
                }
            } catch (DukeException e) {
                printSeparator();
                System.out.println(e.getMessage());
                printSeparator();
                System.out.println();
            }
        }
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        printSeparator();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printSeparator();
        System.out.println();
    }

    public static void printExitMessage() {
        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
        System.out.println();
    }

    public static void handleAddTaskCommand(String command) throws DukeException {
        String[] subArgs = command.split("\\s+");
        String[] subArgsWithoutFirst = Arrays.copyOfRange(subArgs, 1, subArgs.length);
        String taskDescription = String.join(" ", subArgsWithoutFirst);

        Task task = null;
        StringBuilder stringBuilder = new StringBuilder();

        switch (subArgs[0]) {
        case "todo":
            if (taskDescription.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            task = new Todo(taskDescription);
            break;

        case "deadline":
            String by = "";

            boolean enteredBy = false;
            for (int i = 1; i < subArgs.length; i++) {
                if (subArgs[i].equals("/by")) {
                    taskDescription = stringBuilder.toString().stripTrailing();
                    stringBuilder = new StringBuilder();
                    enteredBy = true;
                } else {
                    stringBuilder.append(subArgs[i]);

                    if (i != subArgs.length - 1) {
                        stringBuilder.append(" ");
                    }
                }
            }

            by = stringBuilder.toString();

            if (taskDescription.isEmpty()) {
                throw new DukeException("The task description of a deadline cannot be empty.");
            }

            if (!enteredBy || by.isEmpty()) {
                throw new DukeException("A deadline must have a 'by' date.");
            }

            task = new Deadline(taskDescription, by);
            break;

        case "event":
            String at = "";
            boolean enteredAt = false;

            for (int i = 1; i < subArgs.length; i++) {
                if (subArgs[i].equals("/at")) {
                    taskDescription = stringBuilder.toString().stripTrailing();
                    stringBuilder = new StringBuilder();
                    enteredAt = true;
                } else {
                    stringBuilder.append(subArgs[i]);

                    if (i != subArgs.length - 1) {
                        stringBuilder.append(" ");
                    }
                }
            }

            at = stringBuilder.toString();

            if (taskDescription.isEmpty()) {
                throw new DukeException("The task description of an event cannot be empty.");
            }

            if (!enteredAt || at.isEmpty()) {
                throw new DukeException("An event must have an 'at' date.");
            }

            task = new Event(taskDescription, at);
            break;

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        taskManager.addTask(task);

        printSeparator();
        System.out.println(" Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println(
                " Now you have " + taskManager.getTaskList().size() + " tasks in the list.");
        printSeparator();
        System.out.println();
    }

    public static void printTasks() {
        printSeparator();
        taskManager.printTasks();
        printSeparator();
        System.out.println();
    }

    public static void handleTaskDoneCommand(String command) throws DukeException {
        String[] subArgs = command.split("\\s+");
        if (subArgs.length == 0) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        int index = Integer.parseInt(subArgs[1]);

        if (index <= 0) {
            throw new DukeException("Invalid task number entered.");
        }

        index--;
        markAndPrintTaskAsDone(index);
    }

    public static void markAndPrintTaskAsDone(int index) throws DukeException {
        Task task = taskManager.getTask(index);

        if (task == null) {
            throw new DukeException("The task does not exist.");
        }

        task.markAsDone();

        printSeparator();
        System.out.println(" Nice! I've marked this task as done: ");
        System.out.println("  " + task);
        printSeparator();
        System.out.println();
    }
}
