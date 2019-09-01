package com.util;

public class Printer {

    /**
     * The spacing after padding for each line of text.
     */
    private static final int MARGIN_LENGTH = 1;
    /**
     * The spacing before the horizontal line begin.
     */
    private static final int PADDING_LENGTH = 4;
    /**
     * The length the right most tip of the horizontal line will span.
     */
    private static final int HORIZONTAL_LINE_LENGTH = 64;

    /**
     * Text to be inserted as indent (used by JSON printing)
     */
    private static final String INDENT = repeatChar(4, ' ');
    /**
     * Text to be inserted before every line of text.
     */
    private static final String MARGIN_AND_PADDING = repeatChar(MARGIN_LENGTH + PADDING_LENGTH,
            ' ');

    /**
     * Text to be inserted before the start of the first line and after the last line.
     */
    private static final String HORIZONTAL_LINE = repeatChar(PADDING_LENGTH, ' ')
            + repeatChar(HORIZONTAL_LINE_LENGTH - PADDING_LENGTH, '_')
            + "\n";

    /**
     * Displays duke logo.
     */
    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Print with error decorator prefixing the string.
     *
     * @param str string to be printed
     */
    public static void printError(String str) {
        System.out.print(formatString("☹ OOPS!!! " + str));
    }

    /**
     * Prints a string with formatting; border, margin and padding.
     *
     * @param str string to be printed.
     */
    public static void printString(String str) {
        System.out.print(formatString(str));
    }

    public static String indentString(String str) {
        return indentString(str, INDENT);
    }

    public static String indentString(String str, String ind) {
        StringBuilder formattedString = new StringBuilder();
        for (String s: str.split("\n")) {
            formattedString.append(ind);
            formattedString.append(s);
            formattedString.append("\n");
        }
        return formattedString.toString();
    }

    /**
     * Builds a string with borders and margin and padding for each line.
     *
     * @param str string to be formatted
     * @return resulting formatted string
     */
    private static String formatString(String str) {
        StringBuilder formattedString = new StringBuilder();
        formattedString.append(HORIZONTAL_LINE);
        formattedString.append(indentString(str, MARGIN_AND_PADDING));
        formattedString.append(HORIZONTAL_LINE);
        formattedString.append("\n");
        return formattedString.toString();
    }

    /**
     * Generates a string of repeated characters.
     *
     * @param length number of repetitions
     * @param c      character to be repeated
     * @return resulting repeated character string
     */
    private static String repeatChar(int length, char c) {
        return String.valueOf(c).repeat(Math.max(0, length));
    }
}