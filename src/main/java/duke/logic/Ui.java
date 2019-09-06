package duke.logic;

import duke.task.Task;
import java.util.Scanner;

/**
 * Represents a user interface with methods that allows the programme to interact with users.
 */
public class Ui {
    private Scanner reader;

    public Ui() {
        reader = new Scanner(System.in);
    }

    /**
     * Reads and returns the next line of user input.
     *
     * @return next line of user input.
     */
    public String readNextLine() {
        return reader.nextLine();
    }

    /**
     * Reads and returns the next user input String.
     *
     * @return next user input String.
     */
    public String readNext() {
        return reader.next();
    }

    /**
     * Prints the loading error of the programme.
     */
    public void showLoadingError() {
        System.out.println("File not found.");
    }

    /**
     * Prints a horizontal line in compliance with the user interface.
     */
    public void showLine() {
        StringBuilder lineBuilder = new StringBuilder("     ");
        for(int i = 0; i < 59; i++) {
            lineBuilder.append("_");
        }
        System.out.println(lineBuilder.toString());
    }

    /**
     * Prints a separation line in compliance with the user interface.
     */
    public void separationline() {
        System.out.println();
    }

    /**
     * Prints a farewell statement when user command prompts to terminate the programme.
     */
    public void showBye() {
        this.showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     * Prints a welcome statement when programme starts.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?" );
    }

    /**
     * Prints an informative informing user a task is marked as done.
     *
     * @param task task that has been set as done.
     */
    public void showDone(Task task) {
        this.showLine();
        System.out.println("     Nice! I've marked this duke.task as done:");
        System.out.println("       " + task);
        this.showLine();
        this.separationline();
    }

    /**
     * Prints an informative informing user about a task that has been deleted.
     *
     * @param task task that has been deleted.
     * @param size size of the ArrayList<Task> </Task>.
     */
    public void showDelete(Task task, int size) {
        this.showLine();
        System.out.println("     Nice! I've removed this duke.task:");
        System.out.println("       " + task);
        System.out.println("      Now you have " + size + " tasks in the list.");
        this.showLine();
        this.separationline();
    }

    /**
     * Prints an informative informing user about a task that has been added.
     * @param task task that has been added.
     * @param size size of the ArrayList<Task> </Task>.
     */
    public void showAdd(Task task, int size) {
        this.showLine();
        System.out.println("      Got it. I've added this duke.task:");
        System.out.println("       " + task);
        System.out.println("      Now you have " + size + " tasks in the list.");
        this.showLine();
        this.separationline();
    }

}
