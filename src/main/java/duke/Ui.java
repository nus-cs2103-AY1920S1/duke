package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Ui {
    private InputStream in;
    private PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream inputStream, PrintStream printStream) {
        this.in = inputStream;

        try {
            // Set UTF-8 encoding to print characters like ✓ and ☹
            Charset utf8Charset = Charset.forName("UTF-8");
            PrintStream out = new PrintStream(printStream, true, utf8Charset.name());
            this.out = out;
        } catch (UnsupportedEncodingException e) {
            this.out = printStream;
        }
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void say(String text) {
        out.println(" ____________________________________________________________");
        out.println(text);
        out.println(" ____________________________________________________________");
        out.println("");
    }
    
    public void oops(String text) {
        say("\u2639 OOPS!!! " + text); // ☹
    }

    public void showLoadingError() {
        oops("Couldn't load tasks from disk.");
    }

    public void showWelcome() {
        say("Hello! I'm Duke\nWhat can I do for you?");
    }
}
