import java.util.Scanner;

public class UI {
    // 79 characters, excluding \n. Line is of length 75 characters.
    public static final String LINE = "    ___________________________________________________________________________\n";
    public static final String INDENTATION_LVL1 = "     "; // 5 spaces, for first level indentation.
    public static final String INDENTATION_LVL2 = "       "; // 7 spaces, for second level indentation (i.e. more inner).
    public static final int CHARACTERS_LIMIT = 73; // length that a string to be printed should not exceed.
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

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
     * print the stuff specified by the printFunction, enclosed within two lines.
     *
     * @param printFunction function which print something.
     */
    public void echo(PrintFunction printFunction) {
        System.out.print(LINE);
        printFunction.print();
        System.out.print(LINE);
        System.out.print("\n");
    }

    /**
     * Print the strings provided line by line, enclosed within two long horizontal lines.
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

    // Indent a string using the indentation string given and add a newline character at the back.
    // If the length of the string is more than the number of characters allowed in a line
    // (taking the indentation into account), split the string into separate lines.
    protected String indentAndSplit(String string, String indentation) {
        int lengthLimit = getLengthLimit(indentation.length());

        if (string.length() <= lengthLimit) {
            return String.format("%s%s\n", indentation, string);
        } else {
            return splitIntoLines(string, indentation.length());
        }
    }

    /*
     * Split a given string into lines if it's more than the characters limit allowed in one line
     * , with indentation in front of the string in each line.
     * Indentation Number is the number of spaces in front of a string in each line.
     */
    private String splitIntoLines(String string, int indentation) {
        StringBuilder builder = new StringBuilder(string.length() + 30);
        String indentation_string = getIndentationString(indentation);

        // Remove the spaces in front of the given string first.
        String string_to_be_treated = string.trim();

        int lengthLimit = getLengthLimit(indentation);
        while (true) {
            builder.append(indentation_string);
            builder.append(string_to_be_treated.substring(0, lengthLimit));
            builder.append("\n");

            string_to_be_treated = string_to_be_treated.substring(lengthLimit);
            if (string_to_be_treated.length() <= lengthLimit) {
                builder.append(indentation_string);
                builder.append(string_to_be_treated);
                builder.append("\n");
                break;
            }
        }

        return builder.toString();
    }

    // Return an indentation String.
    private String getIndentationString(int indentation) {
        if (indentation == INDENTATION_LVL1.length()) {
            return INDENTATION_LVL1;
        } else if (indentation == INDENTATION_LVL2.length()) {
            return INDENTATION_LVL2;
        } else {
            StringBuilder identationBuilder = new StringBuilder(indentation);
            for (int i = 0; i < indentation; i++) {
                identationBuilder.append(" ");
            }
            return identationBuilder.toString();
        }
    }

    // Return the number of characters allowed in one line after taking indentation into consideration.
    private int getLengthLimit(int indentation) {
        return CHARACTERS_LIMIT - (indentation - INDENTATION_LVL1.length());
    }

    // Return the phrase "N word" or "N words" (singular or plural).
    // N is the the number of tasks in the taskList.
    public String getTaskPhrase(int size) {
        return size > 1 ? size + " tasks" : size + " task";
    }
}
