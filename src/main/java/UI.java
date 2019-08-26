import java.util.Scanner;

public class Ui {

    private static Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void showLoadingError() {
        System.out.println("Unable to open file. Initialising empty file list.");
    }

    public static String getNextLine() {
        return sc.nextLine();
    }

    public static void outputTaskAdded(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " task in the list.");
    }

    public static void outputTaskRemoved(Task task, TaskList taskList) {
        System.out.println("Got it. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public static void outputTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
    }
}
