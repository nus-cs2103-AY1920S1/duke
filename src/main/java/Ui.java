import java.util.Scanner;

/**
 * Represents the User Interface of Duke, including the user input fields
 */
public class Ui {

    final String lineSpace = "_______________________________\n";
    private Scanner sc;

    /**
     * Default Constructor to initialise Scanner object for user input
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out Welcome Message for the launch of Duke Chatbot
     */
    public void showWelcome() {
        final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String startMessage = lineSpace + "Hello! I'm Duke\nWhat can I do for you?\n" + lineSpace;
        System.out.println("Hello from\n" + logo + startMessage);
    }

    /**
     * Prints Goodbye Message when user closes the Duke Chatbot
     */
    public void showGoodbye() {
        String endMessage = "Bye. Hope to see you again!";
        System.out.println(endMessage);
    }

    /**
     * Prints a straight line for clarity purposes
     */
    public void showLine() {
        System.out.print(lineSpace);
    }

    /**
     * Prints a specified error message
     * @param error Message of error
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Reads the user input and returns a String
     * @return User input
     */
    public String readCommand() {
        String userCmd = sc.next();
        return userCmd;
    }

    /**
     * Returns the description of Task specified in the user input
     * Use only after reading the initial command input of the user
     */
    public String readDesc() {
        String desc = sc.nextLine();
        return desc;
    }

    /**
     * Returns the index of the Task specified in the user input
     * Use only after reading the initial command input of the user
     */
    public int readIndex() {
        int index = sc.nextInt();
        return index;
    }
}
