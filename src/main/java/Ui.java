public class Ui {
    /*
    1. constructor which initialises scanner
    2. void showWelcome()
    3. void showResponse()
    4. void showError()
    5. void exit()
    6. String readWord()
     */
    private String INDENT;

    public Ui(String INDENT) {
        this.INDENT = INDENT;
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
