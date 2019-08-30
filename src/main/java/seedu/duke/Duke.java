package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.DoneCommand;
import seedu.duke.commands.ListCommand;

import seedu.duke.trackables.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    static final String HORIZONTAL_LINE = "______________________________"
            + "______________________________";

    enum CommandName {
        ADD("add"),
        LIST("list"),
        DONE("done"),
        BYE("bye");

        private final String command;

        CommandName(String command) {
            this.command = command;
        }
    }

    static final String LIST_PATTERN = "(list)";
    static final String BYE_PATTERN = "(bye)";
    static final String ADD_PATTERN = "(add)\\s([\\s\\w]+)";
    static final String DONE_PATTERN = "(done)(?=\\s)\\s(\\d+)";

    static boolean continueRunning = true;

    private static List<Task> taskList = new ArrayList<Task>();

    /**
     * Main Method.
     * @param args string arguments for console.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);

        greet();

        Scanner in = new Scanner(System.in);

        while (continueRunning) {
            String input  = in.nextLine();
            parseCommand(input);
        }
    }

    /**
     * Parses the input command into the specific Command object to execute.
     * @param input Input from the console.
     */
    private static void parseCommand(String input) {

        // Identify Command
        String pattern = "";
        CommandName command = null;
        try {
            command = CommandName.valueOf(input.split(" ")[0].toUpperCase());
        }
        catch (IllegalArgumentException iae) {
            System.out.println("No such command");
            return;
        }
        switch (command) {
        case ADD:
            pattern = ADD_PATTERN;
            break;
        case LIST:
            pattern = LIST_PATTERN;
            break;
        case DONE:
            pattern = DONE_PATTERN;
            break;
        case BYE:
            pattern = BYE_PATTERN;
            break;
        default:
            System.out.println("No such command");
            break;
        }

        Command commandToExecute = null;
        Matcher matcher = Pattern.compile(pattern).matcher(input);

        if (matcher.matches()) {

            if (command == CommandName.ADD) {
                commandToExecute = new AddCommand(new Task(matcher.group(2)));
            } else if (command == CommandName.LIST) {
                commandToExecute = new ListCommand();
            } else if (command == CommandName.DONE) {
                commandToExecute = new DoneCommand(Integer.parseInt(matcher.group(2)));
            } else if (command == CommandName.BYE) {
                commandToExecute = new ByeCommand();
            } else {
                System.out.println("Invalid Command");
                return;
            }
        }

        if (commandToExecute != null) {
            commandToExecute.execute(taskList);
        }
    }

    /**
     * Print horizontal line.
     */
    private static void printLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    /**
     * Prints the greeting message.
     */
    private static void greet() {
        printLine();
        System.out.println("\t" + "Hello! I'm Duke\n\t"
                + "What can I do for you?");
        printLine();
    }
}
