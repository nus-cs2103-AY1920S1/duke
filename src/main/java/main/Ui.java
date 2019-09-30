package main;

import java.util.Scanner;

public class Ui {
    Scanner sc;
    String currentLine;

    /**
     * Constructor for gui.Duke app user interface.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Stores the next line for the next user interface response.
     * @param nextLine String for the next user interface response.
     */
    public void nextLine(String nextLine) {
        this.currentLine = nextLine;
    }

    /**
     * Reads the user input and returns the user input.
     * @return String user input.
     */
    public String readCommand() {
        currentLine = sc.nextLine();
        return currentLine;
    }

    /**
     * Prints the current user interface response/reply.
     */
    public String showLine() {
        System.out.println(currentLine);
        String temp = currentLine;
        currentLine = null;
        return temp;
    }

    /**
     * Checks if there is any user interface response.
     * @return
     */
    public boolean hasLineToShow() {
        return (currentLine != null);
    }

    /**
     * Prints out any error messages.
     * @param msg Error message that is printed.
     */
    public void showError(String msg) {
        System.out.println("    ____________________________________________________________\n"
                + "    "
                + msg
                + "\n"
                + "    ____________________________________________________________");
    }

    /**
     * Shows the welcome message of the user interface.
     */
    public String showWelcome() {
        String welcome = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke. Use \"list\" to view your current tasks.\n"
                + "     What can I do for you today?\n"
                + "    ____________________________________________________________";
        System.out.println(welcome);
        return welcome;
    }

    /**
     * Shows the exit message of the user interface.
     */
    public void showExit() {
        sc.close();
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }
}
