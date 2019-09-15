package seedu.duke.ui;

import seedu.duke.commons.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private final Scanner in;
    private final PrintStream out;

    public UI() {
        this(System.in, System.out);
    }

    public UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome() {
        printReply(Messages.MESSAGE_WELCOME_MESSAGE);
    }

    public void showGoodByeMessage() {
        printReply(Messages.MESSAGE_GOODBYE_MESSAGE);
    }

    public void showLoadingError() {
        printReply(Messages.MESSAGE_FILE_NOT_FOUND);
    }

    public void printReply(String reply) {
        System.out.println("\t" + Messages.REPLY_WRAPPER + "\n\t" + reply + "\n\t" + Messages.REPLY_WRAPPER);
    }
}
