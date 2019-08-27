import java.util.Scanner;

public class Ui {

    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    public static void showLine() {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
    }

    public static void greet() {
        echo("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String s) {
        String[] arr = s.split("\n");
        showLine();
        for (String str : arr) {
            System.out.println("     " + str);
        }
        showLine();
    }

    public static void echo(Task t, int x) {
        if (x == 1) {
            echo("Got it. I've added this task: \n  " + t + "\nNow you have " + x + " task in the list.");
        } else {
            echo("Got it. I've added this task: \n  " + t + "\nNow you have " + x + " tasks in the list.");
        }
    }

    public static void printList(TaskList tasklist) {
        Task[] tasks = tasklist.getTasks();
        showLine();
        if (tasks[0] == null) {
            System.out.println("     There are no tasks in your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            int y = 0;
            while (tasks[y] != null) {
                System.out.println("     " + (y + 1) + ". " + tasks[y]);
                y++;
            }
        }
        showLine();
    }

    public static void printDone(Task t) {
        showLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + t);
        showLine();
    }

    public static void printDeleted(Task t, int x) {
        showLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + t);
        if (x == 1) {
            System.out.println("     Now you have " + x + " task in the list.");
        } else {
            System.out.println("     Now you have " + x + " tasks in the list.");
        }
        showLine();
    }

    public static void showError(String string) {
        showLine();
        System.out.println("     " + string);
        showLine();

    }
}
