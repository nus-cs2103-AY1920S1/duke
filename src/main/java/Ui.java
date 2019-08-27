import java.util.Scanner;
import java.lang.StringBuilder;

public class Ui {
    private static final String DUKE_LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DUKE_INTRODUCTION = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String DIVIDER_PROTOTYPE = "____________________________________________________";
    private static final String DIVIDER_BAR = String.format("    %s%s\n", Ui.DIVIDER_PROTOTYPE, Ui.DIVIDER_PROTOTYPE);
    
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(Ui.DUKE_LOGO);
        this.print(Ui.DUKE_INTRODUCTION);
    }

    public boolean hasCommand() {
        return this.sc.hasNext();
    }

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    /** Pretty-prints a given message string to the standard output stream.
     *  Adds indentation and horizontal lines to the message.
     * 
     * @param message a string to embellish then print to <code>System.out</code>.
     */
    public void print(String message) {
        StringBuilder result = new StringBuilder();
        
        result.append(Ui.DIVIDER_BAR)
              .append("\n");

        // Retrieves each individual line in the message and pads them with spaces to the left
        String[] lines = message.split("\n");
        for (String line: lines) {
            result.append("     ")
                  .append(line)
                  .append("\n");
        }
        
        result.append(Ui.DIVIDER_BAR);
        System.out.println(result.toString());
    }
}
