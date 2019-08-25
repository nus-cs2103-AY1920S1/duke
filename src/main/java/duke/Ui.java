package duke;

import java.util.Scanner;

public class Ui {

    private static String HORIZONTAL_LINE = "________________________________________________________________\n";

    private static String greetingMsg = HORIZONTAL_LINE
            + " Hello! I'm Duke\n"
            + " What can I do for you?\n"
            + HORIZONTAL_LINE;

    Scanner scanner;

    public Ui() {

    }

    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    void displayDukeException(DukeExceptions ex) {
        printMsgLine(ex.getDisplayMsg());
    }

    void printGreetingMsg() {
        System.out.println(greetingMsg);
    }

    void printMsg(String msg) {
        System.out.print(msg);
    }

    public void printMsgLine(String msg) {
        System.out.println(msg);
    }

    void printEmptyLine() {
        System.out.println();
    }

    public void printLineDivider() {
        System.out.print(HORIZONTAL_LINE);
    }

    String readLine() {
        return scanner.nextLine();
    }

}
