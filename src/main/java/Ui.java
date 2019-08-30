import java.util.Scanner;

public class Ui {
    // Class attributes
    final static private String INDENT = "      ";

    // Object attributes
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getUserCommand() {
        System.out.println(); // Injects a spacing for future user input
        return sc.nextLine();
    }

    public void displayMessage(String message) {
        System.out.print(this.indentMessage(message));
    }

    public void displayMessage(String message, int extraIndent) {
        System.out.print(this.indentMessage(message, extraIndent));
    }

    public void displaySingleLine(String message) {
        System.out.println(INDENT + message);
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public String indentMessage(String s) {
        String[] lines = s.split("\n");
        StringBuilder indented_output = new StringBuilder();
        for (String line : lines) {
            indented_output.append(INDENT);
            indented_output.append(line);
            indented_output.append("\n");
        }
        return indented_output.toString();
    }

    public String indentMessage(String s, int extraIndent) {
        String[] lines = s.split("\n");
        StringBuilder indented_output = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        sb.append(INDENT);
        for (int i = 0; i < extraIndent; i++) {
            sb.append(" ");
        }
        String indent = sb.toString();

        for (String line : lines) {
            indented_output.append(indent);
            indented_output.append(line);
            indented_output.append("\n");
        }
        return indented_output.toString();
    }

    /*
     Prints out a formatted hello greeting on the
     screen. It is a implemented as a thin wrapper
     around duke.formattedPrintln()
    */
    public void greetHello() {
        String hello = "Hello! I'm Duke\n"
                + "What can I do for you?";
        this.showLine();
        this.displayMessage(hello);
        this.showLine();
    }

    /*
     Prints out a formatted goodbye greeting on the
     screen. It is a implemented as a thin wrapper
     around duke.formattedPrintln()
    */
    public void greetGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        //this.showLine();
        this.displaySingleLine(goodbye);
        //this.showLine();
    }

    public void showLoadingError(DukeException e) {
        this.showLine();
        this.displayMessage("I'm couldn't find a saved task list...\n"
                + "Let me start up a brand new list!");
        this.showLine();
    }

    /*
     Prints the target string between two horizontal
     bars. Adds a newline to the input string
     before printing.

     @param output  The string to be printed
     */
    private void formattedPrintln(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "\n"
                + "____________________________________________________________\n");
    }

    /*
     Prints the target string between two horizontal
     bars. Newline is not added to input string.

     @param output  The string to be printed
    */
    private void formattedPrint(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "____________________________________________________________\n");
    }
}
