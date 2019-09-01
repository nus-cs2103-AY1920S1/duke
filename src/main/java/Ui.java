package seedu.duke;

import seedu.duke.exception.DukeException;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "--------------------------------------------";
    private static final String INDENT = "    ";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void print(String output) {
        System.out.println(INDENT + output);
    }

    public static void printBlock(String[] outputs) {
        print(LINE);
        for (String output : outputs) {
            print(" " + output);
        }
        print(LINE);
    }

    public static void printBlock(String output) {
        print(LINE);
        print(" " + output);
        print(LINE);
    }

    public Parser readCommand() throws DukeException {
        return new Parser(sc.nextLine());
    }
}
