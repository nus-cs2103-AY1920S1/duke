package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private InputStream in;
    private PrintStream out;
    public Ui() {
        this(System.in, System.out);
    }
    public Ui(InputStream inputStream, PrintStream printStream) {
        this.in = inputStream;
        this.out = printStream;
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
        say("☹ OOPS!!! " + text);
    }
    public void showLoadingError() {
        oops("Couldn't load tasks from disk.");
    }
    public void showWelcome() {
        say("Hello! I'm Duke\nWhat can I do for you?");
    }
}
