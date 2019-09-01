package seedu.duke;

import seedu.duke.exception.DukeException;
import java.util.Scanner;

/**
 * Class handling pretty printing and input reading for the program.
 * Note that you do not need to create an instance of this class to print, only for reading input.
 * @author Lim Daekoon
 */
public class Ui {

    private static final String LINE = "--------------------------------------------";
    private static final String INDENT = "    ";

    private Scanner sc;

    /**
     * Constructs the new UI Object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out the specified String with indentation.
     * @param output String to be printed out.
     */
    public static void print(String output) {
        System.out.println(INDENT + output);
    }

    /**
     * Prints out multiple Strings with proper indenting and top and bottom horizontal lines.
     * @param outputs Array containing all the strings to be printed out in a single block.
     */
    public static void printBlock(String[] outputs) {
        print(LINE);
        for (String output : outputs) {
            print(" " + output);
        }
        print(LINE);
    }

    /**
     * Prints out a single line of String within a block.
     * @param output String to be printed out in a block.
     */
    public static void printBlock(String output) {
        print(LINE);
        print(" " + output);
        print(LINE);
    }

    /**
     * Reads in a line of input, and parses it.
     * @return Parser object that parsed the line of input.
     * @throws DukeException If there is a problem with the command read in.
     */
    public Parser readCommand() throws DukeException {
        return new Parser(sc.nextLine());
    }
}
