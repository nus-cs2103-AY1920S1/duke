import java.util.Scanner;

/**
 * Deals with user interaction
 */
public class Ui {
    /**
     * Constant indentation after start of line (formatting)
     */
    private String INDENT;
    /**
     * Scanner for requesting input from the user
     */
    private Scanner scanner;

    /**
     * Constructor for UI to utilise Scanner and indent correctly
     * @param INDENT Constant indentation from start of line (formatting)
     */
    public Ui(String INDENT) {
        this.INDENT = INDENT;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input and returns to other components
     * @return String indicating user line input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Formats input into formatted style (lines and indentation)
     * @param string Input string to be inputted
     * @param INDENT Constant indentation from start of line (formatting)
     */
    public void printIndentedString(String string, String INDENT) {
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + " " + string);
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println();
    }

    /**
     * Greets user and begins program
     */
    public void showWelcome() {
        printIndentedString("Hello! I'm Duke\n" +
                INDENT + " " + "What can I do for you?", INDENT);
    }

    /**
     * Greets user goodbye and ends program
     */
    public void exit() {
        printIndentedString("Bye. Hope to see you again soon!", INDENT);
    }

    /**
     * Indicates error in loading
     */
    public void showLoadingError() {
        printIndentedString("Loading error! New file created.", INDENT);
    }

    /**
     * Indicates missing file error
     */
    public void showFileMissingError() { printIndentedString("File not found. Check file directory.", INDENT); }

    /**
     * Prints input error message
     * @param errorMessage Relevant error message
     */
    public void printError(String errorMessage) {
        printIndentedString(errorMessage, INDENT);
    }

    /**
     * Prints response message to user
     * @param responseMessage Relevant message
     */
    public void printResponse(String responseMessage) {
        printIndentedString(responseMessage, INDENT);
    }
}
