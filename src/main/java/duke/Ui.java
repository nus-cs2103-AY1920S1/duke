package duke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        showLine();
        printStandard("Hello! I'm Duke");
        printStandard("What can I do for you?");
        showLine();
    }

    public void showExit() {
        printStandard("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {

    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String s) {
        printStandard(s);
    }

    public void showMatches(ArrayList<String> matches) {
        printStandard("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); ++i) {
            printStandard(String.format("%d.%s", i + 1, matches.get(i).trim()));
        }
    }

    public void showAddedTask(String task, int lstSize) {
        printStandard("Got it. I've added this task:");
        printStandard(task);
        showTaskSize(lstSize);
    }

    public void showDeletedTask(String task, int lstSize) {
        printStandard("Noted. I've removed this task:");
        printStandard(task);
        showTaskSize(lstSize);
    }

    public void showDoneTask(String task) {
        printStandard("Nice! I've marked this task as done:");
        printStandard(task);
    }

    private void showTaskSize(int size) {
        printStandard(String.format("Now you have %d tasks in the list.", size));
    }

    public void showAllTasks(LinkedList<String> taskLst) {
        printStandard("Here are the tasks in your list:");
        for (String task : taskLst) {
            printStandard(task);
        }
    }

    private void printStandard(String string) {
        System.out.println("     " + string);
    }
}
