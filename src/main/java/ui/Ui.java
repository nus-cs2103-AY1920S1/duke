package ui;

import java.util.Scanner;

/**
 * User interface to communicate with user.
 * Responsible for every output printing.
 */
public class Ui {
    protected String horizontalLine;
    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.horizontalLine = "    ____________________________________________________________";
    }

    /**
     * Shows a horizontal line which acts as a divider.
     */
    public void showLine() {
        System.out.println(horizontalLine);
    }

    /**
     * Shows greeting message at start of the program.
     */
    public void showWelcome() {
        System.out.println(horizontalLine);
        System.out.println("     Hello from! I'm Duke\n" + "     What can I do for you?");
        System.out.println(horizontalLine + "\n");
    }

    /**
     * Read in user inputs/command, line by line.
     * @return user inputs as String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out the error.
     * @param e the error message.
     */
    public void showError(String e) {
        System.out.println("     " + e);
    }

    /**
     * Prints out error about unable to load data stored in user's hard disk.
     */
    public void showLoadingError() {
        System.out.println(horizontalLine);
        System.out.println("     Note: task.TaskList storage is initially empty / the file is corrupted");
        System.out.println("     New empty file will be created.");
        System.out.println(horizontalLine + "\n");
    }
}