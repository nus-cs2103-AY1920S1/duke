public class DukeFormatter {
    /**
     * Horizontal line of width 60 characters, for demarcating Duke's responses
     */
    protected static final String horizontalLine =
            "    ____________________________________________________________";

    /**
     * 4 space indentation for pretty-printing Duke's responses
     */
    protected static final String indent = "    ";

    /**
     * Prints the given text with four spaces indentation
     * @param text      Formatted text with appropriate line breaks. Maximum
     *                  width for each line is 60 characters.
     */
    public static void prettyPrint(String text) {
        String[] lines = text.split("\n");
        System.out.println(horizontalLine);
        for (String line : lines) {
            System.out.println(indent + line);
        }
        System.out.println(horizontalLine);
    }
}
