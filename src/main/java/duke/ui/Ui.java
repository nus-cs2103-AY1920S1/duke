package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public static final String LS = System.lineSeparator();
    public static final String INDENT = "  ";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void closeScanner() {
        in.close();
    }

    /**
     * Prints a platform independent line separator.
     */
    public void showLine() {
        out.print(LS);
    }

    public void printResponse(String response) {
        out.println(response);
    }
}
