package duke;

import java.util.Scanner;

/**
 * Handles all interface actions for Duke.
 * Reads input and prints output.
 */
public class Ui {
    private static final String LINE_BREAK = "______________________________________________________________________";
    private static final String HELLO_STRING = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE_STRING = "Bye. Hope to see you again soon!";
    private static final String ERROR_PREPEND = "☹ OOPS!!!";
    private Scanner scanner;

    // field that tracks the Ui's last said string
    private String lastSaid;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.lastSaid = "";
    }

    public String getLastSaid() {
        return this.lastSaid;
    }

    /**
     * Reads a line of input from the user
     * @return The input line as String
     */
    public String readLine() {
        String input = "";
        if(scanner.hasNextLine())
            input = scanner.nextLine().trim();
        return input;
    }

    /**
     * Prints a standard greeting
     */
    public void greet() {
        say(HELLO_STRING);
    }

    /**
     * Prints a standard farewell
     */
    public void farewell() {
        say(BYE_STRING);
    }

    /**
     * Prints a specified error message
     * @param sequence An error message
     */
    public void sayError(String sequence) {
        say(String.format("%s %s",
                ERROR_PREPEND,
                sequence));
    }

    /**
     * Prettifies any String and prints it
     * @param sequence The String to be printed
     */
    public void say(String sequence) {
        System.out.println(sequence);
        lastSaid = sequence;
    }

    public void CliSay(String sequence) {
        String indented = sequence.replaceAll("(?m)^", "\t\t");
        String message = String.format("\t%s\n%s\n\t%s\n",
                LINE_BREAK,
                indented,
                LINE_BREAK);
        System.out.print(message);
        lastSaid = message;
    }

    public void close() {
        this.scanner.close();
    }
}
