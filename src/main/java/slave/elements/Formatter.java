package slave.elements;

/**
 * A class that formats strings to be printed.
 */
class Formatter {

    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

    /**
     * Formats input message.
     *
     * @param text Message to be formatted.
     * @return Formatted message.
     */
    static String formatMessage(String text) {
        return HORIZONTAL_LINE + "\n"
                + INDENTATION + text + "\n"
                + HORIZONTAL_LINE + "\n";
    }

    /**
     * Prints a Line.
     */
    static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Adds indentation (5 spaces).
     *
     * @param text Text to be indented.
     * @return Indented text.
     */
    static String indentLine(String text) {
        return INDENTATION + text;
    }

}
