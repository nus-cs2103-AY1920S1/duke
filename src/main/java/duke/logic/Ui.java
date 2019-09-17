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
     * Prints the loading error of the programme.
     */
    public void showLoadingError() {
        System.out.println("File not found.");
    }

    /**
     * Prints a horizontal line in compliance with the user interface.
     */
    public String showSeparationLine() {
        StringBuilder lineBuilder = new StringBuilder("     ");
        for (int i = 0; i < 54; i++) {
            lineBuilder.append("_");
        }
        lineBuilder.append("\n");
        return lineBuilder.toString();
    }

    /**
     * Prints a separation line in compliance with the user interface.
     */
    public String showBlankLine() {
        return "\n";
    }

    /**
     * Prints a welcome statement when programme starts.
     */
    public String showWelcome() {
        StringBuilder welcomeBuilder = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        welcomeBuilder.append("Hello from\n");
        welcomeBuilder.append(logo);
        welcomeBuilder.append("Hello! I'm Duke\nWhat can I do for you?\n");
        return welcomeBuilder.toString();
    }

    /**
     * Prints a farewell statement when user command prompts to terminate the programme.
     */
    public String showBye() {
        StringBuilder byeBuilder = new StringBuilder();
        byeBuilder.append(this.showSeparationLine());
        byeBuilder.append("     Bye. Hope to see you again soon!\n");
        byeBuilder.append(this.showSeparationLine());
        return  byeBuilder.toString();
    }

    /**
     * Prints an informative informing user a task is marked as done.
     *
     * @param task task that has been set as done.
     */
    public String showDone(Task task) {
        StringBuilder doneBuilder = new StringBuilder();
        doneBuilder.append(this.showSeparationLine());
        doneBuilder.append("     Nice! I've marked this task as done:\n       ");
        doneBuilder.append(task);
        doneBuilder.append("\n");
        doneBuilder.append(this.showSeparationLine());
        return doneBuilder.toString();
    }

    /**
     * Prints an informative informing user about a task that has been deleted.
     *
     * @param task task that has been deleted.
     * @param size number of tasks in the ArrayList<Task> </Task>.
     */
    public String showDelete(Task task, int size) {
        assert size >= 0 : "size of taskList must be at least zero";
        StringBuilder deleteBuilder = new StringBuilder();
        deleteBuilder.append(this.showSeparationLine());
        deleteBuilder.append("     Nice! I've removed this task:\n       ");
        deleteBuilder.append(task);
        deleteBuilder.append("\n");
        deleteBuilder.append("      Now you have ");
        deleteBuilder.append(size);
        deleteBuilder.append(" task(s) in the list.\n");
        deleteBuilder.append(this.showSeparationLine());
        return deleteBuilder.toString();
    }

    /**
     * Prints an informative informing user about a task that has been added.
     * @param task task that has been added.
     * @param size size of the ArrayList<Task> </Task>.
     */
    public String showAdd(Task task, int size) {
        assert size >= 0 : "size of taskList must be at least zero";
        StringBuilder addBuilder = new StringBuilder();
        addBuilder.append(this.showSeparationLine());
        addBuilder.append("      Got it. I've added this task:\n       ");
        addBuilder.append(task);
        addBuilder.append("\n      Now you have " + size + " task(s) in the list.\n");
        addBuilder.append(this.showSeparationLine());
        addBuilder.append(this.showBlankLine());
        return addBuilder.toString();
    }
    public String showMatchingTasks(ArrayList<Task> listOfMatches) {
        StringBuilder tasksBuilder = new StringBuilder();
        tasksBuilder.append(this.showSeparationLine());
        tasksBuilder.append("      Here are the matching task(s) in your list:\n");
        int counter = 1;
        for(Task task : listOfMatches) {
            tasksBuilder.append("     ");
            tasksBuilder.append(counter);
            tasksBuilder.append(".");
            tasksBuilder.append(task);
            tasksBuilder.append("\n");
            counter++;
        }
        tasksBuilder.append(this.showSeparationLine());
        tasksBuilder.append(this.showBlankLine());
        return tasksBuilder.toString();
    }

}
