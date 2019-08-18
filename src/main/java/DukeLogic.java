import java.util.Scanner;

public class DukeLogic {

    /*===============================
    ||    Private Static Strings   ||
    =================================*/

    private final String DUKE_ASCII_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String DUKE_WELCOME_MESSAGE = "Hello! I'm Duke\n\t What can I do for you?";
    private final String SEPARATOR = "____________________________________________________________";
    private final String DUKE_EXIT_COMMAND = "bye";
    private final String DUKE_EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private Scanner scanner;

    /*===============================
    ||  Private Auxiliary Methods  ||
    =================================*/

    /**
     * Prints supplied input wrapped with "______" separator.
     * @param input String to display to user.
     */
    private void displayToUser(String input) {
        System.out.println(encapsulateOutputWithSeparator(input));
    }

    /**
     * Takes an input String and wrap it with "______" separator before returning the new updated String.
     * @param input String to wrap the separators around.
     * @return String that is wrapped around the separators.
     */
    private String encapsulateOutputWithSeparator(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t" + SEPARATOR + "\n").append("\t " + input + "\n").append("\t" + SEPARATOR + "\n");
        return sb.toString();
    }

    /**
     * Initialize resources to be used by this DukeLogic class. Class-scope variables are instantialized in this method
     * as it will be ran before accepting user-input for commands.
     */
    private void initializeResources() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads in user-input as a String before checking the input. If the command is to terminate the program, the
     * exit method will be called. Otherwise, the user-input will be mirrored back to the user in a formatted style.
     */
    private void handleUserInput() {
        String input = scanner.nextLine();
        if (input.equals(DUKE_EXIT_COMMAND)) {
            terminateProgram();
        } else {
            displayToUser(input);
        }
    }

    /**
     * Performs cleanup (if any) and prints the Duke exit message. Program then exits.
     */
    private void terminateProgram() {
        displayToUser(DUKE_EXIT_MESSAGE);
        System.exit(0);
    }

    /*===============================
    ||       Public Methods        ||
    =================================*/

    /**
     * Displays the Duke ASCII logo followed by the welcome message to the user.
     */
    public void displayWelcomeMessage() {
        System.out.println(DUKE_ASCII_LOGO);
        displayToUser(DUKE_WELCOME_MESSAGE);
    }

    /**
     * Method first initializes the resources needed for this class before continuously reading user-input.
     */
    public void run() {
        initializeResources();

        while (true) {
            handleUserInput();
        }
    }

}
