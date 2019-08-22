import java.util.ArrayList;

/**
 * Encapsulates attributes and behaviour of Duke, a personal assistant chatbot.
 *
 * Duke greets the user, stores commands entered by the user, and exits when the
 * user types 'bye'. Duke can retrieve stored commands in a user readable format.
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
    private boolean isListening;

    /** a list of all commands entered by the user */
    private ArrayList<String> commands;

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

        commands = new ArrayList<String>();
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
        } else if (command.equalsIgnoreCase("list")) {
            // user wants to print all commands entered
            printCommands();
        } else {
            // add command to commands list and inform user
            storeCommand(command);
        }
    }

    /**
     * Exits the chatbot application with an exit message
     */
    private void exit() {
        System.out.print(exitMessage);
    }

    /**
     * Returns the unique logo of Duke
     * @return a string representation of Duke's a logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Returns the current listening state of Duke
     * @return true if Duke is currently listening to commands, false otherwise
     */
    public boolean getIsListening() {
        return isListening;
    }

    /**
     * Prints all commands entered by the user in a readable format
     */
    private void printCommands() {
        // inform user if the list is empty
        if (commands.isEmpty()) {
            System.out.println("No tasks in your list!");
        } else {
            int listSize = commands.size(); // find number of commands entered
            for (int i = 0; i < listSize; i++) {
                // specified format: "1. task 1"
                System.out.format("%d. %s\n", i + 1, commands.get(i));
            }
        }
    }

    /**
     * Stores commands entered by the user
     *
     * @param command the command entered by the user
     */
    private void storeCommand(String command) {
        commands.add(command);
        System.out.println("added: " + command);
    }
}