import java.util.Scanner;

public class Duke {
    public static final String HORIZONTAL_LINE = "   ____________________________________________________________";
    public static final String COMMAND_INDENTATION = "    ";
    public static void main(String[] args) {
        greetingMessage();
        readCommands();
    }

    /**
     * Format the welcome message and print it
     */
    public static void greetingMessage() {
        printLines(HORIZONTAL_LINE, COMMAND_INDENTATION + "Hello! I'm Duke",
                COMMAND_INDENTATION + "What can I do for you?", HORIZONTAL_LINE, "");
    }

    /**
     * Prints message to the console
     * @param messagesLines message to be printed to the console
     */
    public static void printLines(String ... messagesLines) {
        for (String line : messagesLines) {
            System.out.println(line);
        }
    }

    /**
     * Keep reading user commands until user enters "bye"
     */
    public static void readCommands() {
        Scanner myScanner = new Scanner(System.in);
        while (myScanner.hasNext()) {
            String command = myScanner.next();
            if (command.equals("bye")) {
                printLines(HORIZONTAL_LINE, COMMAND_INDENTATION + "Bye. Hope to see you again soon!", HORIZONTAL_LINE);
                break;
            } else {
                printLines(HORIZONTAL_LINE, COMMAND_INDENTATION + command, HORIZONTAL_LINE, "");
            }
        }
    }
}
