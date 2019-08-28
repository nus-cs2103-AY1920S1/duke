package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

// This class deals with interactions with the user.
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String UI_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(UI_GREETING);
    }

    public void showLine() {
        String UI_HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(UI_HORIZONTAL_LINE);
    }

    public void showBye() {
        String UI_GOODBYE = "Bye. Hope to see you again soon!";
        sc.close();
        System.out.println(UI_GOODBYE);
    }

    public void showLoadingError() {
        System.out.println("Error in loading tasks into Duke.");
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    public void showAddedTask(char firstAlphabet, boolean isDone, String taskDescription, int numberOfItems) {
        char icon = isDone ? '\u2713' : '\u274C';
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + firstAlphabet + "][" + icon + "] " + taskDescription);
        System.out.println("Now you have " + numberOfItems + " in the list.");
    }

    public void showList(ArrayList<String> listToPrint) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listToPrint.size(); i++) {
            System.out.println((i + 1) + "." + listToPrint.get(i));
        }
    }

    public void showDeletedTask(Task t, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.printTask());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showDoneTask(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.printTask());
    }

    public void showIndexError() {
        System.out.println("Invalid task number. Please check again.");
    }

    public void showInputError() {
        System.out.println("Error! Please check input again");
    }

    public void showCommandNotFoundError() {
        System.out.println("Command not found. Please check command again.");
    }
}
