package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * This class provides all the input/output functionality needed to interact with the user.
 * It is a thin wrapper over an {@link InputStream} and a {@link PrintStream}.
 */
public class Ui {
    private InputStream in;
    private PrintStream out;

    /**
     * Constructs a Ui using the default {@link System#in} and {@link System#out}.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui using the specified streams.
     *
     * @param inputStream  the stream to receive user input from
     * @param printStream  the stream to print user output to
     */
    public Ui(InputStream inputStream, PrintStream printStream) {
        this.in = inputStream;

        try {
            // Set UTF-8 encoding to print characters like ✓ and ☹
            Charset utf8Charset = Charset.forName("UTF-8");
            PrintStream out = new PrintStream(printStream, true, utf8Charset.name());
            this.out = out;
        } catch(UnsupportedEncodingException e) {
            this.out = printStream;
        }
    }

    /**
     * Returns the next line of user input.
     *
     * @return  the String that was read
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints a String in a stylised ASCII text box.
     *
     * @param text  the String to be printed
     */
    public void say(String text) {
        out.println(" ____________________________________________________________");
        out.println(text);
        out.println(" ____________________________________________________________");
        out.println("");
    }

    /**
     * Prepends an exclamatory phrase to a String and prints it in a stylised ASCII text box.
     * The result is the same as calling {@link #say} with the argument <code>"☹ OOPS!!!" + text</code>.
     *
     * @param text  the String to be printed
     */
    public void oops(String text) {
        say("\u2639 OOPS!!! " + text); // ☹
    }

    /**
     * Prints an error message about the failure to load tasks from disk.
     */
    public void showLoadingError() {
        oops("Couldn't load tasks from disk.");
    }

    /**
     * Prints a message welcoming the user to Duke.
     */
    public void showWelcome() {
        say("Hello! I'm Duke\nWhat can I do for you?");
    }
}
