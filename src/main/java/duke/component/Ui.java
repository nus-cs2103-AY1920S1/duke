package duke.component;

import duke.support.PrintFunction;

import java.util.Scanner;

/**
 * Encapsulates and serves as the user interface of duke bot. It provides several methods to read inputs and
 * print outputs to the console.
 */
public class Ui {
    /** A line used to enclose message to be printed. 79 characters, excluding \n. Line is of length 75 characters. **/
    public static final String LINE = "    ___________________________________________________________________________\n";
    /** 5 spaces, for first level indentation. **/
    public static final String INDENTATION_LVL1 = "     ";
    /** 7 spaces, for second level indentation (i.e. more inner). */
    public static final String INDENTATION_LVL2 = "       ";
    /** The number of characters allowed in each printed line */
    public static final int CHARACTERS_LIMIT = 73;
    private Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the input from the console.
     * The space in front and behind of the input is removed.
     *
     * @return the processed input.
     */
    public String readInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints welcome message to the console.
     */
    public void printWelcomeMessage() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        System.out.print(LINE);
        System.out.print(logo + "\n"
                + INDENTATION_LVL1 + "Hello! I'm Duke\n"
                + INDENTATION_LVL1 + "What can I do for you?\n");
        System.out.print(LINE);
        System.out.print("\n");
    }

    /**
     * Prints the messages specified in the print() method of the PrintFunction object, enclosed within two lines.
     *
     * @param printFunction function which specifies the messages to be printed and printing logic.
     */
    public void echo(PrintFunction printFunction) {
        System.out.print(LINE);
        printFunction.print();
        System.out.print(LINE);
        System.out.print("\n");
    }

    /**
     * Prints the strings provided line by line, enclosed within two long horizontal lines.
     * Each line is indented by 5 spaces.
     *
     * @param strings strings to be printed.
     */
    public void echo(String... strings) {
        echo(() -> {
            for (String string : strings) {
                System.out.print(indentAndSplit(string, INDENTATION_LVL1));
            }
        });
    }

    /**
     * Indents a string using the indentation string given and add a newline character at the back.
     * If the length of the string is more than the number of characters allowed in a line
     * (taking the indentation into account), split the string into separate lines.
     *
     * @return the indented and split string.
     */
    public String indentAndSplit(String string, String indentation) {
        int lengthLimit = getLengthLimit(indentation.length());

        if (string.length() <= lengthLimit) {
            return String.format("%s%s\n", indentation, string);
        } else {
            return splitIntoLines(string, indentation.length());
        }
    }

    /**
     * Splits a given string into lines if it's more than the characters limit allowed in one line
     * , with indentation in front of the string in each line.
     * Indentation Number is the number of spaces in front of a string in each line.
     *
     * @return the split and indented string.
     */
    private String splitIntoLines(String string, int indentationLength) {
        StringBuilder builder = new StringBuilder(string.length() + 30);
        String indentation = getIndentationString(indentationLength);

        // Remove the spaces in front of the given string first.
        String string_to_be_treated = string.trim();

        // Calculate the character limits of a line after taking the indentation into account.
        int lengthLimit = getLengthLimit(indentationLength);
        while (true) {
            // Split the front part of the string until the character that the string becomes too long,
            // then append that front part to the string builder.
            builder.append(indentation);
            builder.append(string_to_be_treated.substring(0, lengthLimit));
            builder.append("\n");

            // Update the string to be treated to be the remaining part of the string.
            // Stops processing the string if the length of the string does not exceed the characters limit.
            string_to_be_treated = string_to_be_treated.substring(lengthLimit);
            if (string_to_be_treated.length() <= lengthLimit) {
                builder.append(indentation);
                builder.append(string_to_be_treated);
                builder.append("\n");
                break;
            }
        }

        return builder.toString();
    }

    /**
     * Returns an indentation String of the indentation length specified.
     *
     * @param indentationLength The number of spaces in the indentation.
     * @return the indentation string.
     */
    private String getIndentationString(int indentationLength) {
        if (indentationLength == INDENTATION_LVL1.length()) {
            return INDENTATION_LVL1;
        } else if (indentationLength == INDENTATION_LVL2.length()) {
            return INDENTATION_LVL2;
        } else {
            StringBuilder identationBuilder = new StringBuilder(indentationLength);
            for (int i = 0; i < indentationLength; i++) {
                identationBuilder.append(" ");
            }
            return identationBuilder.toString();
        }
    }

    /**
     * Returns the number of characters allowed in one line after taking indentation into consideration.
     */
    private int getLengthLimit(int indentation) {
        return CHARACTERS_LIMIT - (indentation - INDENTATION_LVL1.length());
    }

    /**
     * Returns the phrase "N word" or "N words" (singular or plural).
     * N is the the number of tasks in the taskList.
     */
    public String getTaskPhrase(int size) {
        return size > 1 ? size + " tasks" : size + " task";
    }
}
