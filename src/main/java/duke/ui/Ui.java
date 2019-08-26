package duke.ui;

import duke.DukeExceptions;

import java.util.Scanner;

public class Ui {

    private static final String HORIZONTAL_LINE = "________________________________________________________________\n";

    private static final String greetingMsg = HORIZONTAL_LINE
            + " Hello! I'm Duke\n"
            + " What can I do for you?\n"
            + HORIZONTAL_LINE;

    private Scanner scanner;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayDukeException(DukeExceptions ex) {
        printMsgLine(ex.getDisplayMsg());
    }

    public void printGreetingMsg() {
        System.out.println(greetingMsg);
    }

    public void printMsgLine(String msg) {
        System.out.println(msg);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printLineDivider() {
        System.out.print(HORIZONTAL_LINE);
    }

    public String readLine() {
        return scanner.nextLine();
    }

}
