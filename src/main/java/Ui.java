import java.util.Scanner;

public class Ui {
    /*
    6. String readWord()
     */
    private String INDENT;
    private Scanner scanner;

    public Ui(String INDENT) {
        this.INDENT = INDENT;
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printIndentedString(String string, String INDENT) {
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + " " + string);
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println();
    }

    public void start() {
        printIndentedString("Hello! I'm Duke\n" +
                INDENT + " " + "What can I do for you?", INDENT);
    }

    public void exit() {
        printIndentedString("Bye. Hope to see you again soon!", INDENT);
    }

    public void printError(String errorMessage) {
        printIndentedString(errorMessage, INDENT);
    }

    public void printResponse(String responseMessage) {
        printIndentedString(responseMessage, INDENT);
    }
}
