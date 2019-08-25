public class Duke {
    public static void main(String[] args) {
        Printer.printString("Hello! I'm Duke\nWhat can I do for you?");
    }
}

class Printer {

    private static final int MARGIN_LENGTH = 1;
    private static final int PADDING_LENGTH = 4;
    private static final int HORIZONTAL_LINE_LENGTH = 64;

    private static final String MARGIN_AND_PADDING = repeatChar(MARGIN_LENGTH + PADDING_LENGTH, ' ');
    private static final String HORIZONTAL_LINE = repeatChar(PADDING_LENGTH, ' ')
            + repeatChar(HORIZONTAL_LINE_LENGTH - PADDING_LENGTH,'_')
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

    public static void printString(String str) {
        System.out.print(formatString(str));
    }

    public static String formatString(String str) {
        StringBuilder formattedString = new StringBuilder();
        formattedString.append(HORIZONTAL_LINE);
        for (String s : str.split("\n")) {
            formattedString.append(MARGIN_AND_PADDING);
            formattedString.append(s);
            formattedString.append("\n");
        }
        formattedString.append(HORIZONTAL_LINE);
        formattedString.append("\n");
        return formattedString.toString();
    }

    private static String repeatChar(int length, char c) {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < length; i++) {
            s.append(c);
        }
        return s.toString();
    }
}