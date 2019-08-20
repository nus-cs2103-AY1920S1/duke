import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static Integer totalTask = 0;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        greet();

        while(scanner.hasNext()) {
            String event = scanner.nextLine();

            if (event.equals("bye")) {
                exit();
                break;
            } else if (event.equals("list")) {
                printList(taskManager);
            } else if (event.startsWith("done")) {
                String[] words = event.split("\\s");
                int index = Integer.parseInt(words[1]);
                doneTask(taskManager, index);
            } else {
                addTask(taskManager, event);
                totalTask++;
            }

        }

    }

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        horizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
        System.out.println();
    }

    public static void exit() {
        horizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static void addTask(TaskManager taskManager, String event) {
        horizontalLine();
        taskManager.addTask(new Task(event));
        System.out.println("added: " + event);
        horizontalLine();
        System.out.println();
    }

    public static void printList(TaskManager taskManager) {
        horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalTask; i++) {
            System.out.println(i+1 + ".[" + taskManager.getTask(i).getStatusIcon() + "] " + taskManager.getTask(i).getDescription());
        }
        horizontalLine();
        System.out.println();
    }

    public static void doneTask(TaskManager taskManager, int index) {
        index = index - 1;
        taskManager.doneTask(index);
        horizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + taskManager.getTask(index).getStatusIcon() + "] " + taskManager.getTask(index).getDescription());
        horizontalLine();
        System.out.println();
    }

}
