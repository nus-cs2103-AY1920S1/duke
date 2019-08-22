import java.util.Scanner;

/**
 * Encapsulates attributes and behaviour of Duke, a personal assistant chatbot.
 *
 * Duke can greet the user with a welcome message, echo commands from the user,
 * and exit when the user types 'bye'.
 *
 * @author atharvjoshi
 * @contributors j-lum, damithc
 * @version CS2103 AY19/20 Sem 1 iP Week 2
 */
public class Duke {
    /** a unique logo for Duke */
    private final String logo;

    /** welcome message */
    private String welcomeMessage;

    /** exit message */
    private String exitMessage;

    /** flag to indicate if Duke is listening to commands from the user */
    public boolean isListening;

    /**
     * Creates and initialises an instance of Duke. Duke's logo is assigned
     * only once.
     */
    public Duke() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        welcomeMessage = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

        exitMessage = "Bye. Hope to see you again soon!\n";

        // when an instance of Duke is created, it starts listening to commands
        isListening = true;
    }

    /**
     * Returns the unique logo of Duke
     * @return a string representation of Duke's a logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Greets the user with the welcome message
     */
    public void greet() {
        System.out.print(welcomeMessage);
    }

    /**
     * Processes a command entered by the user.
     *
     * @param command a string containing the command(s) entered by the user
     */
    public void processCommand(String command) {
        if (command.equalsIgnoreCase("bye")) {
            // user wants to quit, so Duke stops listening to commands and exits
            isListening = false;
            exit();
        } else {
            // duke simply echoes all other commands entered by the user
            System.out.println(command);
        }
    }

    /**
     * Exits the chatbot application with an exit message
     */
    private void exit() {
        System.out.print(exitMessage);
    }
}