package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private static final String REPLY_WRAPPER = "_______________________________________________________________";
    private final Scanner in;
    private final PrintStream out;

    public UI(){
        this(System.in, System.out);
    }

    public UI(InputStream in, PrintStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome(){
        printReply("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public String readCommand(){
        return in.nextLine();
    }

    public void showGoodByeMessage(){
        printReply("Bye. Hope to see you again soon.");
    }

    public void showLoadingError(){
        printReply("No file found.");
    }

    public void printReply(String reply){
        System.out.println("\t" + REPLY_WRAPPER + "\n\t" + reply + "\n\t" + REPLY_WRAPPER);
    }
}
