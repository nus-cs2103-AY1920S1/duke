import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String START_HORIZONTAL_LINE = "   ____________________________________________________________";
    public static final String END_HORIZONTAL_LINE = "   ____________________________________________________________\n";
    public static final String COMMAND_INDENTATION = "    ";
    public static final String BYE_MESSAGE = COMMAND_INDENTATION + "Bye. Hope to see you again soon!";
    public static final String GREETING_MESSAGE = COMMAND_INDENTATION + "Hello! I'm Duke\n" +
            COMMAND_INDENTATION + "What can I do for you?";
    public static final String ADDED_MESSAGE = "added: ";

    private List<String> commandHistoryList = new ArrayList<>();
    private Scanner myScanner;
    public static void main(String[] args) {
        Duke myDuke = new Duke();
        myDuke.run();
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        myScanner = new Scanner(System.in);
        displayGreetingMessage();
        while (myScanner.hasNextLine()) {
            String command = readCommand();
            executeCommand(command);
        }
    }

    /**
     * Formats the welcome message and print it.
     */
    public void displayGreetingMessage() {
        printLines(START_HORIZONTAL_LINE, GREETING_MESSAGE, END_HORIZONTAL_LINE);
    }

    /**
     * Prints message to the console.
     * @param messagesLines message to be printed to the console
     */
    public void printLines(String ... messagesLines) {
        for (String line : messagesLines) {
            System.out.println(line);
        }
    }

    /**
     * Reads user command by the user.
     * @return commands entered by the user
     */
    public String readCommand() {
        return myScanner.nextLine().trim();
    }

    /**
     * Saves command entered by the user.
     * @param command the last command entered by the user
     */
    public void addCommandsEntered(String command) {
        commandHistoryList.add(command);
    }

    /**
     * Runs the commands entered by the user.
     * @param command is the last command entered by the user
     */
    public void executeCommand(String command) {
        if (command.equals("bye")) {
            printLines(START_HORIZONTAL_LINE, BYE_MESSAGE, END_HORIZONTAL_LINE);
            System.exit(0);
        } else if (command.equals("list")) {
            String formattedString = getCommandsHistory();
            printLines(START_HORIZONTAL_LINE, COMMAND_INDENTATION + formattedString, END_HORIZONTAL_LINE);
        } else {
            addCommandsEntered(command);
            printLines(START_HORIZONTAL_LINE, COMMAND_INDENTATION + ADDED_MESSAGE + command, END_HORIZONTAL_LINE);
        }
    }

    /**
     * Formats the history of user commands.
     * @return formatted history of commands
     */
    public String getCommandsHistory() {
        StringBuilder myStringBuilder = new StringBuilder();
        for (int i = 0; i < commandHistoryList.size(); i++) {
            if (i < commandHistoryList.size() - 1) {
                myStringBuilder.append(i + 1).append(". ").append(commandHistoryList.get(i)).append("\n").append(COMMAND_INDENTATION);
            } else if (i == commandHistoryList.size() - 1) {
                myStringBuilder.append(i + 1).append(". ").append(commandHistoryList.get(i));
            }
        }
        return myStringBuilder.toString();
    }
}
