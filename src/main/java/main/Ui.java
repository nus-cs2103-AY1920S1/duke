package main;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {

    public static final String DIVIDER = "    ____________________________________________________________";
    public static final String BLANKSPACE = "     ";
    private Scanner sc;
    private String currLine;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the command from the user input.
     * @return The user input command, with leading and trailing whitespaces removed.
     * @throws DukeException In event of an empty command
     */
    public String readCommand() throws DukeException {
        String fullCommand = sc.nextLine().trim();
        if (fullCommand.equals("")) {
            throw new DukeException("Please enter a command. Type 'help' for a list of all valid commands.");
        }
        currLine = fullCommand;
        return fullCommand;
    }

    /**
     * Shows the divider line
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Shows the Duke welcome message
     */
    public void showWelcome() {
        showLine();
        dukeEcho("Hi! My name is Duke, what can I do for you?");
        showLine();
    }

    /**
     * Shows a goodbye message from Duke
     */
    public void showGoodbye() {
        dukeEcho("Bye! Hope to see you again soon!");
    }

    /**
     * Shows an error
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        dukeEcho(message);
    }

    /**
     * Outputs to the user.
     * @param messages The list of messages to be shown to the user.
     *                 Messages in separate lines should be fed in as different parameters, e.g.
     *                 dukeEcho("This is message 1", "Message 2 is on the next line");
     */
    public void dukeEcho(String... messages){
        for (String msg : messages) {
            System.out.println(BLANKSPACE + msg);
        }
    }


}
