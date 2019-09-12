package seedu.duke;

import seedu.duke.commands.*;


import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.storage.Storage;
import seedu.duke.trackables.Deadline;
import seedu.duke.trackables.Event;
import seedu.duke.trackables.Task;
import seedu.duke.trackables.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    static final String HORIZONTAL_LINE = "______________________________"
        + "______________________________";

    enum CommandName {
        LIST("list"),
        ADD("add"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        BYE("bye");

        private final String command;

        CommandName(String command) {
            this.command = command;
        }
    }

    private static final String LIST_PATTERN = "(list)";
    private static final String ADD_PATTERN = "(add)(?=\\s)\\s([\\w\\D]+)";
    private static final String DONE_PATTERN = "(done)(?=\\s)\\s([\\d]+)";
    private static final String TODO_PATTERN = "(todo)(?=\\s)\\s([\\w\\D]+)";
    private static final String DEADLINE_PATTERN = "(deadline)(?=\\s)\\s([\\w\\D]+(?=\\s/by\\s))\\s/by\\s([\\w\\D]+)";
    private static final String EVENT_PATTERN = "(event)(?=\\s)\\s([\\w\\D]+(?=\\s/at\\s))\\s/at\\s([\\w\\D]+)";
    private static final String DELETE_PATTEN = "(delete)(?=\\s)\\s([\\d]+)";
    private static final String BYE_PATTERN = "(bye)";


    private static boolean continueRunning = true;

    private static List<Task> taskList = new ArrayList<Task>();

    /**
     * Main Method.
     *
     * @param args string arguments for console.
     */
    public static void main(String[] args) throws Storage.StorageOperationException {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);

        greet();

        taskList = Storage.getInstance().loadFromDisk();

        Scanner in = new Scanner(System.in);

        while (continueRunning) {
            String input = in.nextLine();
            try {
                parseCommand(input);
            } catch (InvalidCommandException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Parses the input command into the specific Command object to execute.
     *
     * @param input Input from the console.
     */
    private static void parseCommand(String input) throws InvalidCommandException
        , Storage.StorageOperationException {

        // Identify Command
        CommandName command = null;
        try {
            command = CommandName.valueOf(input.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new InvalidCommandException("No Such command found", iae);
        }

        Command commandToExecute = null;
        String pattern = getPattern(command);
        Matcher matcher = Pattern.compile(pattern).matcher(input);

        if (matcher.matches()) {

            if (command == CommandName.LIST) {
                commandToExecute = new ListCommand();
            } else if (command == CommandName.ADD) {
                commandToExecute = new AddCommand(matcher.group(2));
            } else if (command == CommandName.DONE) {
                int taskId = Integer.parseInt(matcher.group(2));
                commandToExecute = new DoneCommand(taskId);
            } else if (command == CommandName.TODO) {
                commandToExecute = new TodoCommand(matcher.group(2));
            } else if (command == CommandName.EVENT) {
                commandToExecute = new EventCommand(matcher.group(2), matcher.group(3));
            } else if (command == CommandName.DEADLINE) {
                commandToExecute = new DeadlineCommand(matcher.group(2), matcher.group(3));
            } else if (command == CommandName.DELETE) {
                int taskId = Integer.parseInt(matcher.group(2));
                commandToExecute = new DeleteCommand(taskId);
            } else if (command == CommandName.BYE) {
                commandToExecute = new ByeCommand();
                Storage.getInstance().saveToDisk(taskList);
            } else {
                // Code should never reach here. If it does, return to caller.
                return;
            }
        } else {
            commandToExecute = new ErrorCommand(
                new InvalidArgumentException("Invalid or missing arguments in command "
                    + command.name() + ".", null));
        }

        try {
            commandToExecute.execute(taskList);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    private static String getPattern(CommandName command) {

        String pattern = "";
        switch (command) {
        case LIST:
            pattern = LIST_PATTERN;
            break;
        case ADD:
            pattern = ADD_PATTERN;
            break;
        case DONE:
            pattern = DONE_PATTERN;
            break;
        case TODO:
            pattern = TODO_PATTERN;
            break;
        case EVENT:
            pattern = EVENT_PATTERN;
            break;
        case DEADLINE:
            pattern = DEADLINE_PATTERN;
            break;
        case DELETE:
            pattern = DELETE_PATTEN;
            break;
        case BYE:
            pattern = BYE_PATTERN;
            break;
        default:
            break;
        }
        return pattern;
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
