package main;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
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
     *
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
     * Shows the divider line.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Returns a welcome message from duke
     *
     * @return a welcome message from duke
     */
    public String getWelcomeMessage() {
        String res = dukeEchoString("Hi! My name is Duke, what can I do for you?");
        return res;
    }

    /**
     * Returns a goodbye message from Duke.
     *
     * @return a goodbye message from Duke
     */
    public String getGoodbyeMessage() {
        String res = dukeEchoString("Bye! Hope to see you again soon!");
        return res;
    }

    /**
     * Displays an error.
     *
     * @param message the error message to be displayed
     * @return error message from Duke
     */
    public String getErrorMessage(String message) {
        String res = dukeEchoString(message);
        return res;
    }

    /**
     * Outputs to the user.
     *
     * @param messages The list of messages to be shown to the user.
     *                 Messages in separate lines should be fed in as different parameters, e.g.
     *                 dukeEcho("This is message 1", "Message 2 is on the next line");
     */
    public void dukeEcho(String... messages) {
        for (String msg : messages) {
            System.out.println(BLANKSPACE + msg);
        }
    }

    /**
     * Creates a string output format for the GUI.
     *
     * @param messages The list of messages to be shown to the user.
     *                 Messages in separate lines should be fed in as different parameters, e.g.
     *                 dukeEcho("This is message 1", "Message 2 is on the next line");
     */
    public String dukeEchoString(String... messages) {
        if (messages.length==0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : messages) {
            sb.append(BLANKSPACE + msg + "\n");
        }
        return sb.toString();
    }


}
