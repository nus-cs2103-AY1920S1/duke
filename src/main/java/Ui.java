import java.util.Scanner;

/**
 * Represents the User Interface used by the Dike App. It is responsible for reading and displaying
 * everything to and from the user. It keeps track of only the latest user input.
 */
public class Ui {
    public String lastCommand = "";

    /**
     * Constructs a UI object.
     */
    public Ui() {
    }

    /**
     * Retrievese the latest user input.
     * @return the latest user input.
     */
    public String getLastCommand() {
        return this.lastCommand;
    }

    /**
     * Reads the user input, and returns it as a string to be processed.
     * @return the user input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        this.lastCommand = sc.nextLine();
        return lastCommand;
    }

    /**
     * Indicates when there is an error loading a savefile.
     */
    void showLoadingError() {
        System.out.println("    Error Reading File");
    }

    /**
     * Displayes a message with a 4 space indentation.
     * @param msg the message to be shown to the user.
     */
    void showMessage(String msg) {
        System.out.println("    " + msg);
    }

    /**
     * Displayes a message after a particular number of spaces.
     * @param numOfSpace The number of spaces that need to be displayed first before the message.
     * @param msg The message to be displayed.
     */
    void showMessage(int numOfSpace, String msg) {
        for (int i = 0; i < numOfSpace; i++) {
            System.out.print(" ");
        }
        System.out.println(msg);
    }

    /**
     * Displayes an error text
     * @param err the text of the error.
     */
    void showError(String err) {
        System.out.println("    " + err);
    }

    /**
     * Shows the welcome text.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Type 'help' to view available commands");
        System.out.println("How may I help you?\n");
    }

    /**
     * Displayes a horizontal line.
     */
    public void straightLine() {
        System.out.println("\n--------------------------------------");
    }
}
