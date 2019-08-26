package duke;

import java.util.Scanner;

public class Ui {
    private static final String LINE_BREAK = "______________________________________________________________________";
    private static final String HELLO_STRING = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE_STRING = "Bye. Hope to see you again soon!";
    private static final String ERROR_PREPEND = "☹ OOPS!!!";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public void greet() {
        say(HELLO_STRING);
    }

    public void farewell() {
        say(BYE_STRING);
    }

    public void sayError(String sequence) {
        say(String.format("%s %s",
                ERROR_PREPEND,
                sequence));
    }

    public void say(String sequence) {
        String indented = sequence.replaceAll("(?m)^", "\t\t");
        System.out.printf(
                "\t%s\n%s\n\t%s\n",
                LINE_BREAK,
                indented,
                LINE_BREAK
        );
    }

    public void close() {
        this.scanner.close();
    }
}
