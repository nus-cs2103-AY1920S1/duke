package ui;

import java.util.Scanner;

/**
 * Cli class.
 *
 * @author Marcus Ong
 */
public class Cli extends Ui {
    private static final String LINE_PREPEND = "\t";
    private static final String LINE_APPEND = "\n";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    protected StringBuilder messageBuilder = new StringBuilder(LINE_PREPEND + HORIZONTAL_LINE + LINE_APPEND);

    public Cli() {
    }

    /**
     * Read a string input from user.
     *
     * @return String containing user input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Show message to user.
     */
    @Override
    public void show() {
        addLineToMessage(HORIZONTAL_LINE);
        String output = messageBuilder.toString();
        messageBuilder = new StringBuilder(LINE_PREPEND + HORIZONTAL_LINE + LINE_APPEND);
        System.out.println(output);
    }

    /**
     * Adds a line to the message.
     *
     * @param line String containing a line to add to messageBuilder.
     */
    @Override
    public void addLineToMessage(String line) {
        messageBuilder.append(LINE_PREPEND);
        messageBuilder.append(line);
        messageBuilder.append(LINE_APPEND);
    }
}
