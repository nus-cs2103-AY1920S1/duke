import java.util.List;

class DukeFormatter {
    private static final String horizontalLine =
            "____________________________________________________________";

    private static final String indent = "    ";

    /**
     * Prints a horizontal line of width 60 characters.
     */
    private static void printBorder() {
        System.out.println(indent + horizontalLine);
    }

    /**
     * Prints the given text with indentation of five spaces.
     * @param text
     */
    private static void print(String text) {
        System.out.println(indent + " " + text);
    }

    /**
     * Prints the given text with a horizontal line above and below the text
     * and appropriate indentation.
     * @param text      Formatted text with appropriate line breaks. Maximum
     *                  width for each line is 58 characters.
     */
    static void prettyPrint(String text) {
        String[] lines = text.split("\n");
        printBorder();
        for (String line : lines) {
            print(line);
        }
        printBorder();
    }

    /**
     * Prints the items in the given list with their corresponding index
     * numbers. A horizontal line is printed above and below the list, and
     * output is indented throughout.
     * @param list      A List of Tasks to be printed.
     */
     // TODO: Wrap text for longer user input
     static void prettyPrint(List<Task> list) {
        printBorder();
        for (int i = 1; i <= list.size(); i++) {
            print(i + "." + list.get(i - 1).toString());
        }
        printBorder();
     }
}
