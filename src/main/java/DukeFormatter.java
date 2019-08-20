import java.util.List;

class DukeFormatter {
    /**
     * Horizontal line of width 60 characters, for demarcating Duke's responses
     */
    protected static final String horizontalLine =
            "____________________________________________________________";

    /**
     * 4 space indentation for pretty-printing Duke's responses
     */
    protected static final String indent = "    ";

    private static void print(String text) {
        System.out.println(indent + text);
    }

    /**
     * Prints the given text with a horizontal line above and below the text
     * and four spaces of indentation throughout.
     * @param text      Formatted text with appropriate line breaks. Maximum
     *                  width for each line is 58 characters.
     */
    static void prettyPrint(String text) {
        String[] lines = text.split("\n");
        print(horizontalLine);
        for (String line : lines) {
            print(line);
        }
        print(horizontalLine);
    }

    /**
     * Prints the items in the given list with their corresponding index
     * numbers. A horizontal line is printed above and below the list, and
     * output is indented by four spaces throughout.
     * @param list      A List of Strings to be printed. Each string in the
     *                  list should not exceed 54 characters in length.
     */
    // TODO: Wrap text for longer user input
     static void prettyPrint(List<String> list) {
        print(horizontalLine);
        for (int i = 1; i <= list.size(); i++) {
            print(i + ". " + list.get(i - 1));
        }
        print(horizontalLine);
    }
}
