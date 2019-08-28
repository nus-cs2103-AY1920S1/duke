package duke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message during startup
     */
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

    /**
     * Prints farewell message when "bye" is entered
     */
    public void showExit() {
        printStandard("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {

    }

    /**
     * Prints the standard line output for visual differentiation between user input and system output
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Reads a line of user input via a Scanner variable whenever the "enter" key is pressed
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the error message of the DukeException being thrown
     */
    public void showError(String s) {
        printStandard(s);
    }

    /**
     * Prints all matching tasks to the keyword
     */
    public void showMatches(ArrayList<String> matches) {
        printStandard("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); ++i) {
            printStandard(String.format("%d.%s", i + 1, matches.get(i).trim()));
        }
    }

    /**
     * Prints the most recent task that was added
     */
    public void showAddedTask(String task, int lstSize) {
        printStandard("Got it. I've added this task:");
        printStandard(task);
        showTaskSize(lstSize);
    }

    /**
     * Prints the most recent task that was deleted
     */
    public void showDeletedTask(String task, int lstSize) {
        printStandard("Noted. I've removed this task:");
        printStandard(task);
        showTaskSize(lstSize);
    }

    /**
     * Prints the most recent task that was marked as done
     */
    public void showDoneTask(String task) {
        printStandard("Nice! I've marked this task as done:");
        printStandard(task);
    }

    /**
     * Prints the task list size for both add & delete
     */
    private void showTaskSize(int size) {
        printStandard(String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Prints all the tasks in the TaskList and numbers them
     */
    public void showAllTasks(LinkedList<String> taskLst) {
        printStandard("Here are the tasks in your list:");
        for (String task : taskLst) {
            printStandard(task);
        }
    }

    /**
     * Converts and prints any given String into the correct output format
     */
    private void printStandard(String string) {
        System.out.println("     " + string);
    }
}
