package seedu.duke;

import java.util.Scanner;

public class Ui {
    private static int idleCount;
    protected static Scanner sc;
    protected static String line = "____________________________________________________________";

    public Ui() {
        sc = new Scanner(System.in);
        idleCount = 0;
    }

    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(format(logo + "\n Hello! I'm Duke\n What can I do for you?"));
    }

    public static String format(String s) {
        return line + "\n " + s + "\n" + line;
    }

    public static void showLine() {
        System.out.println(line);
    }

    public static void showError(String str) {
        System.out.println(format(str));
    }

    public static String readCommand() {
        return sc.nextLine();
    }

    public static void showLoadingError() {
        System.out.println(format("There seems to be a problem with loading"));
    }

    public static void doneLine(String str) {
        System.out.println("Nice! I've marked this task as done:\n " + str);
    }

    public static void deleteLine(String str, int size) {
        System.out.println("Noted. I've removed this task: \n   " + str + "\n Now you have " +
        size + " tasks in the list.");
    }

    public static void addLine(String str, int size) {
        System.out.println("Got it. I've added this task:\n " + str +
                "\n Now you have " + size + " tasks in the list.");
    }

    public static void exitLine() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(TaskList tl) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tl.list.size(); i++) {
            System.out.println(" " + i + ". " + tl.list.get(i - 1).toString());
        }
    }
}
