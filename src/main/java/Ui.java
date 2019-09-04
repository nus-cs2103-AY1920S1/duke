import java.util.Scanner;

/**
 * Deals with user interaction.
 */
public class Ui {
    /**
     * Constant indentation after start of line (formatting).
     */
    private String indent;
    /**
     * Scanner for requesting input from the user.
     */
    private Scanner scanner;

    /**
     * Constructor for UI to utilise Scanner and indent correctly.
     * @param indent Constant indentation from start of line (formatting)
     */
    public Ui(String indent) {
        this.indent = indent;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input and returns to other components.
     * @return String indicating user line input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Formats input into formatted style (lines and indentation).
     * @param string Input string to be inputted
     * @param indent Constant indentation from start of line (formatting)
     */
    public void printIndentedString(String string, String indent) {
        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + " " + string);
        System.out.println(indent + "____________________________________________________________");
        System.out.println();
    }

    /**
     * Greets user and begins program.
     */
    public void showWelcome() {
        printIndentedString("Hello! I'm Duke\n"
                + indent + " " + "What can I do for you?", indent);
    }

    /**
     * Greets user goodbye and ends program.
     */
    public void exit() {
        printIndentedString("Bye. Hope to see you again soon!", indent);
    }

    /**
     * Indicates error in loading.
     */
    public void showLoadingError() {
        printIndentedString("Loading error! New file created.", indent);
    }

    /**
     * Indicates missing file error.
     */
    public void showFileMissingError() {
        printIndentedString("File not found. Check file directory.", indent);
    }

    /**
     * Prints input error message.
     * @param errorMessage Relevant error message
     */
    public void printError(String errorMessage) {
        printIndentedString(errorMessage, indent);
    }

    /**
     * Prints response message to user.
     * @param responseMessage Relevant message
     */
    public void printResponse(String responseMessage) {
        printIndentedString(responseMessage, indent);
    }
}
