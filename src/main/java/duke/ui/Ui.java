package duke.ui;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                      + "|  _ \\ _   _| | _____ \n"
                      + "| | | | | | | |/ / _ \\\n"
                      + "| |_| | |_| |   <  __/\n"
                      + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ------------------------------------------------------------");
    }

    public void showError(String message) {
        String output = "    ------------------------------------------------------------\n"
                + "    \uD83D\uDE41 OOPS!! " + message + " \uD83D\uDE41 \n"
                + "    ------------------------------------------------------------";
        System.out.println(output);
    }

    public void showLoadingError() {
        System.out.println("* FAILED TO LOAD DATA");
    }

    public void showLineError(int lineCount, String line) {
        System.out.println("* Unable to parse line " +lineCount + " : " + line);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showTable(String table) {
        System.out.println("    ============================================================");
        System.out.println("    Here are the tasks in your list:");
        System.out.println("    ------------------------------------------------------------");
        System.out.print(table);
        System.out.println("    ============================================================");
    }

    public void showAddInformation(String task, int size) {
        System.out.println("     ------------------------------------------------------------");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     \u2795  " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println("     ------------------------------------------------------------");
    }

    public void showMarkedAsDone(String task) {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println("    ------------------------------------------------------------");
    }

    public void showDeletedMessage(String task, int size) {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    \uD83D\uDDD1  " + task);
        System.out.println("    Now you have " + size + " tasks in the list.");
        System.out.println("    ------------------------------------------------------------");
    }

    public void showExitMessage() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ------------------------------------------------------------");
    }

}
