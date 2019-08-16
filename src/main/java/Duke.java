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
                String[] subArgs = command.split("\\s+");
                if (subArgs.length > 0) {
                    int index = Integer.parseInt(subArgs[1]);

                    if (index >= 1) {
                        index--;
                        markAndPrintTaskAsDone(index);
                    }
                }
            } else {
                addTask(command);
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

    public static void addTask(String task) {
        if (task.isEmpty()) {
            return;
        }

        taskManager.addTask(new Task(task));

        printSeparator();
        System.out.println(" added: " + task);
        printSeparator();
        System.out.println();
    }

    public static void printTasks() {
        printSeparator();
        taskManager.printTasks();
        printSeparator();
        System.out.println();
    }

    public static void markAndPrintTaskAsDone(int index) {
        printSeparator();
        taskManager.markAsDone(index);
        printSeparator();
        System.out.println();
    }
}
