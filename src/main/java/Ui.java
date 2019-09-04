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
//    public void printIndentedString(String string, String indent) {
//        System.out.println(indent + "____________________________________________________________");
//        System.out.println(indent + " " + string);
//        System.out.println(indent + "____________________________________________________________");
//        System.out.println();
//    }

    /**
     * Returns message to greet user and begin program.
     */
    public String showWelcome() {
        return "Hello! I'm Duke\n" + indent + " " + "What can I do for you?";
    }

    /**
     * Returns message to greet user goodbye and end program.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns error message in loading.
     */
    public String showLoadingError() {
        return "Loading error! New file created.";
    }

    /**
     * Returns input error message.
     * @param errorMessage Relevant error message
     */
    public String printError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns response message to user.
     * @param responseMessage Relevant message
     */
    public String printResponse(String responseMessage) {
        return responseMessage;
    }
}
