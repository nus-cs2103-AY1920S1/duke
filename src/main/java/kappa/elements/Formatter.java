package kappa.elements;

/**
 * A class that formats strings to be printed.
 */
class Formatter {

    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private static final String INDENTATION = "     ";

    /**
     * Formats input message.
     *
     * @param text Message to be formatted.
     * @return Formatted message.
     */
    static String formatMessage(String text) {
        return HORIZONTAL_LINE
                + INDENTATION + text + "\n"
                + HORIZONTAL_LINE;
    }

    /**
     * Returns a format line.
     *
     * @return Formatted Horizontal Line
     */
    static String getLine() {
        return HORIZONTAL_LINE;
    }

    /**
     * Adds indentation (5 spaces).
     *
     * @param text Text to be indented.
     * @return Indented text.
     */
    static String indentLine(String text) {
        return INDENTATION + text + "\n";
    }

}
