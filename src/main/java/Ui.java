import java.util.Scanner;

public class Ui {
    // Class attributes
    final static private String INDENT = "  ";

    // Object attributes
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getUserCommand() {
        return sc.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(this.indentMessage(message));
    }

    public void displayMessage(String message, int extraIndent) {
        System.out.println(this.indentMessage(message, extraIndent));
    }

    public void displaySingleLine(String message) {
        System.out.println(INDENT + message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
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
        StringBuilder in = new StringBuilder();

        in.append(INDENT);
        for (int i = 0; i < extraIndent; i++) {
            in.append(" ");
        }
        String indent = in.toString();

        for (String line : lines) {
            indented_output.append(indent);
            indented_output.append(line);
            indented_output.append("\n");
        }
        return indented_output.toString();
    }

//    // Print out all tasks in the current list
//    private void printList() {
//        if (this.list.size() == 0) {
//            this.formattedPrintln("Hey! There's nothing in your list!");
//        } else {
//            int index = 1;
//            Iterator iter = this.list.iterator();
//            StringBuilder output = new StringBuilder();
//
//            output.append("Here are the tasks in your list:\n");
//            while (iter.hasNext()) {
//                output.append("  ");
//                output.append(index);
//                output.append(".");
//                output.append(iter.next());
//                output.append("\n");
//                index++;
//            }
//            this.formattedPrint(output.toString());
//        }
//    }

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


//    /*
//     Prints the target string between two horizontal
//     bars. Adds a newline to the input string
//     before printing.
//
//     @param output  The string to be printed
//     */
//    private void formattedPrintln(String output) {
//        System.out.println("____________________________________________________________\n"
//                + output
//                + "\n"
//                + "____________________________________________________________\n");
//    }
//
//    /*
//     Prints the target string between two horizontal
//     bars. Newline is not added to input string.
//
//     @param output  The string to be printed
//    */
//    private void formattedPrint(String output) {
//        System.out.println("____________________________________________________________\n"
//                + output
//                + "____________________________________________________________\n");
//    }
}
