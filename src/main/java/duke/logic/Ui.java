package duke.logic;

import duke.task.Task;
import java.util.ArrayList;
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
    public String showLine() {
        StringBuilder lineBuilder = new StringBuilder("     ");
        for (int i = 0; i < 59; i++) {
            lineBuilder.append("_");
        }
        lineBuilder.append("\n");
        return lineBuilder.toString();
    }

    /**
     * Prints a separation line in compliance with the user interface.
     */
    public String separationline() {
        return "\n";
    }

    /**
     * Prints a farewell statement when user command prompts to terminate the programme.
     */
    public String showBye() {
        return this.showLine() + "     Bye. Hope to see you again soon!\n" + this.showLine();
    }

    /**
     * Prints a welcome statement when programme starts.
     */
    public String showWelcome() {
        StringBuilder tempBuilder = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        tempBuilder.append("Hello from\n");
        tempBuilder.append(logo);
        tempBuilder.append("Hello! I'm Duke\nWhat can I do for you?\n");
        return tempBuilder.toString();
    }

    /**
     * Prints an informative informing user a task is marked as done.
     *
     * @param task task that has been set as done.
     */
    public String showDone(Task task) {
        return this.showLine() + "     Nice! I've marked this task as done:\n       " + task + "\n" +
                this.showLine() + this.separationline();
    }

    /**
     * Prints an informative informing user about a task that has been deleted.
     *
     * @param task task that has been deleted.
     * @param size size of the ArrayList<Task> </Task>.
     */
    public String showDelete(Task task, int size) {
        return this.showLine() + "     Nice! I've removed this task:\n       " + task + "\n" +
                "      Now you have " + size + " tasks in the list.\n" + this.showLine() + this.separationline();
    }

    /**
     * Prints an informative informing user about a task that has been added.
     * @param task task that has been added.
     * @param size size of the ArrayList<Task> </Task>.
     */
    public String showAdd(Task task, int size) {
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append(this.showLine());
        tempBuilder.append("      Got it. I've added this task:\n       ");
        tempBuilder.append(task);
        tempBuilder.append("\n      Now you have " + size + " tasks in the list.\n");
        tempBuilder.append(this.showLine());
        tempBuilder.append(this.separationline());
        return tempBuilder.toString();
    }
    public String showMatchingTasks(ArrayList<Task> listOfMatches) {
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append(this.showLine());
        tempBuilder.append("      Here are the matching tasks in your list:\n");
        int counter = 1;
        for(Task task : listOfMatches) {
            tempBuilder.append("     ");
            tempBuilder.append(counter);
            tempBuilder.append(".");
            tempBuilder.append(task);
            tempBuilder.append("\n");
            counter++;
        }
        tempBuilder.append(this.showLine());
        tempBuilder.append(this.separationline());
        return tempBuilder.toString();
    }

}
