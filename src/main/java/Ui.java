import java.util.Scanner;

public class Ui {
    private String INDENT;
    private Scanner scanner;

    public Ui(String INDENT) {
        this.INDENT = INDENT;
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printIndentedString(String string, String INDENT) {
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + " " + string);
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println();
    }

    public void showWelcome() {
        printIndentedString("Hello! I'm Duke\n" +
                INDENT + " " + "What can I do for you?", INDENT);
    }

    public void exit() {
        printIndentedString("Bye. Hope to see you again soon!", INDENT);
    }

    public void showLoadingError() {
        printIndentedString("Loading error! New file created.", INDENT);
    }

    public void showFileMissingError() { printIndentedString("File not found. Check file directory.", INDENT); }

    public void printError(String errorMessage) {
        printIndentedString(errorMessage, INDENT);
    }

    public void printResponse(String responseMessage) {
        printIndentedString(responseMessage, INDENT);
    }
}
