package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UiCli extends Ui {
    private static final String LINE_INDENT = "\t";
    private static final String HORIZONTAL_LINE = LINE_INDENT + "_".repeat(60);
    private static final Pattern LINE_START_PATTERN = Pattern.compile("^", Pattern.MULTILINE);

    private final Scanner scanner;
    protected PrintStream output = System.out;

    public UiCli() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets a line of input from the user.
     *
     * @return A line of input from the user.
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line to the user.
     */
    public void printHorizontalLine() {
        output.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an empty line to the user.
     */
    @Override
    public void println() {
        output.println();
    }

    /**
     * Prints the content argument with indentation.
     *
     * @param content Text to display to the user.
     */
    @Override
    public void println(String content) {
        content = LINE_START_PATTERN.matcher(content).replaceAll(LINE_INDENT);
        output.println(content);
    }

    /**
     * Prints a horizontal line, the argument and another horizontal line
     * while taking care of indentation to create a block user interface element.
     *
     * @param content Text to display to the user.
     */
    @Override
    public void printBlock(String content) {
        printHorizontalLine();
        println(content);
        printHorizontalLine();
    }

}
