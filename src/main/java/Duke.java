import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while (scanner.hasNext()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                printExitMessage();
                break;
            } else if (command.equals("list")) {
                printTasks();
            } else if (command.startsWith("done")) {
                handleTaskDoneCommand(command);
            } else {
                handleAddTaskCommand(command);
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

    public static void handleAddTaskCommand(String command) {
        if (command.isEmpty()) {
            return;
        }

        String[] subArgs = command.split("\\s+");
        if (subArgs.length > 1) {
            String[] subArgsWithoutFirst = Arrays.copyOfRange(subArgs, 1, subArgs.length);
            String taskDescription = String.join(" ", subArgsWithoutFirst);

            Task task = null;
            StringBuilder stringBuilder = new StringBuilder();

            switch (subArgs[0]) {
            case "todo":
                task = new Todo(taskDescription);
                break;

            case "deadline":
                String by = "";

                for (int i = 1; i < subArgs.length; i++) {
                    if (subArgs[i].equals("/by")) {
                        taskDescription = stringBuilder.toString().stripTrailing();
                        stringBuilder = new StringBuilder();
                    } else {
                        stringBuilder.append(subArgs[i]);

                        if (i != subArgs.length - 1) {
                            stringBuilder.append(" ");
                        }
                    }
                }

                by = stringBuilder.toString();
                task = new Deadline(taskDescription, by);
                break;

            case "event":
                String at = "";

                for (int i = 1; i < subArgs.length; i++) {
                    if (subArgs[i].equals("/at")) {
                        taskDescription = stringBuilder.toString().stripTrailing();
                        stringBuilder = new StringBuilder();
                    } else {
                        stringBuilder.append(subArgs[i]);

                        if (i != subArgs.length - 1) {
                            stringBuilder.append(" ");
                        }
                    }
                }

                at = stringBuilder.toString();
                task = new Event(taskDescription, at);
                break;

            default:
                return;
            }

            taskManager.addTask(task);

            printSeparator();
            System.out.println(" Got it. I've added this task: ");
            System.out.println("  " + task);
            System.out.println(" Now you have " + taskManager.getTaskList().size() + " tasks in the list.");
            printSeparator();
            System.out.println();
        }
    }

    public static void printTasks() {
        printSeparator();
        taskManager.printTasks();
        printSeparator();
        System.out.println();
    }

    public static void handleTaskDoneCommand(String command) {
        String[] subArgs = command.split("\\s+");
        if (subArgs.length > 0) {
            int index = Integer.parseInt(subArgs[1]);

            if (index >= 1) {
                index--;
                markAndPrintTaskAsDone(index);
            }
        }
    }

    public static void markAndPrintTaskAsDone(int index) {
        Task task = taskManager.getTask(index);

        if (task != null) {
            task.markAsDone();

            printSeparator();
            System.out.println(" Nice! I've marked this task as done: ");
            System.out.println("  " + task);
            printSeparator();
            System.out.println();
        }
    }
}
