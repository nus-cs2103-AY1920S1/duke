package duke;

import java.util.Scanner;

class Ui {

    static String HORIZONTAL_LINE = "________________________________________________________________\n";

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
        printLineDivider();
        System.out.println(ex.getDisplayMsg());
        printLineDivider();
        System.out.println();
    }

    void printGreetingMsg() {
        System.out.println(greetingMsg);
    }

    void printLineDivider() {
        System.out.print(HORIZONTAL_LINE);
    }

    String readLine() {
        return scanner.nextLine();
    }

}
