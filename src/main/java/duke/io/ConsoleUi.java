package duke.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * This class provides all the input/output functionality needed to interact with the user.
 * It is a thin wrapper over an {@link InputStream} and a {@link PrintStream}.
 */
public class ConsoleUi implements UiInput, UiOutput {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String OOPS_PREFIX = "\u2639 OOPS!!! "; // ☹

    private InputStream in;
    private PrintStream out;
    private Scanner scanner;

    /**
     * Constructs a ConsoleUi using the default {@link System#in} and {@link System#out}.
     */
    public ConsoleUi() {
        this(System.in, System.out);
    }

    /**
     * Constructs a ConsoleUi using the specified streams.
     *
     * @param inputStream  the stream to receive user input from
     * @param printStream  the stream to print user output to
     */
    public ConsoleUi(InputStream inputStream, PrintStream printStream) {
        this.in = inputStream;

        try {
            // Set UTF-8 encoding to print characters like ✓ and ☹
            Charset utf8Charset = Charset.forName("UTF-8");
            PrintStream out = new PrintStream(printStream, true, utf8Charset.name());
            this.out = out;
        } catch (UnsupportedEncodingException e) {
            this.out = printStream;
        }
        scanner = new Scanner(this.in);
    }

    /**
     * Returns the next line of user input.
     *
     * @return  the String that was read
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a String in a stylised ASCII text box.
     *
     * @param text  the String to be printed
     */
    public void say(String text) {
        out.println(HORIZONTAL_LINE);
        out.println(text);
        out.println(HORIZONTAL_LINE);
        out.println("");
    }

    /**
     * Prepends an exclamatory phrase to a String and prints it in a stylised ASCII text box.
     * The result is the same as calling {@link #say} with the argument <code>"☹ OOPS!!!" + text</code>.
     *
     * @param text  the String to be printed
     */
    public void oops(String text) {
        say(OOPS_PREFIX + text);
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
