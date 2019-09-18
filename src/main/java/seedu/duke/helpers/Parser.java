package seedu.duke.helpers;

import seedu.duke.Duke;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeadlineCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DoneCommand;
import seedu.duke.commands.ErrorCommand;
import seedu.duke.commands.EventCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.TodoCommand;
import seedu.duke.exceptions.InvalidArgumentException;
import seedu.duke.exceptions.InvalidCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    enum CommandName {
        LIST("list"),
        ADD("add"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find"),
        BYE("bye"),
        ERROR("");

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
    private static final String DELETE_PATTERN = "(delete)(?=\\s)\\s([\\d]+)";
    private static final String FIND_PATTERN = "(find)(?=\\s)\\s([\\w\\D]+)";
    private static final String BYE_PATTERN = "(bye)";

    /**
     * Parses command inputs using regular expressions to extract commands and arguments.
     *
     * @param input Command input from the user.
     * @return Returns the Command to be executed. An ErrorCommand will be returned should the command
     *         be invalid or contain invalid arguments.
     */
    public static Command parseCommand(String input) {

        CommandName command = identifyCommandName(input);

        // Exit if CommandName cannot be identified
        if (command == CommandName.ERROR) {
            return new ErrorCommand(new InvalidCommandException());
        }

        // Extract arguments from input.
        Command commandToExecute = null;
        String pattern = getPattern(command);
        Matcher matcher = Pattern.compile(pattern).matcher(input);

        // Exit if arguments cannot be matched.
        if (!matcher.matches()) {
            commandToExecute = new ErrorCommand(
                new InvalidArgumentException("Invalid or missing arguments in command "
                    + command.name() + "."));
            return commandToExecute;
        }

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
        } else if (command == CommandName.FIND) {
            commandToExecute = new FindCommand(matcher.group(2));
        } else if (command == CommandName.BYE) {
            commandToExecute = new ByeCommand();
        }

        return commandToExecute;
    }

    /**
     * Identifies the CommandName from the input.
     *
     * @param input The complete command input from the user.
     * @return Returns the respective CommandName Enum if a match is found, otherwise the Error CommandName
     *         is returned.
     */
    public static CommandName identifyCommandName(String input) {
        CommandName commandName;
        try {
            String extractedCommand = input.split(" ")[0].toUpperCase();
            commandName = CommandName.valueOf(extractedCommand);
        } catch (IllegalArgumentException iae) {
            commandName = CommandName.ERROR;
        }
        return commandName;
    }

    /**
     * Returns the pattern to use based on the CommandName Enum.
     *
     * @param commandName The CommandName Enum once identified.
     * @return
     */
    private static String getPattern(CommandName commandName) {

        String pattern = "";
        switch (commandName) {
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
            pattern = DELETE_PATTERN;
            break;
            case FIND:
            pattern = FIND_PATTERN;
            break;
        case BYE:
            pattern = BYE_PATTERN;
            break;
        default:
            break;
        }
        return pattern;
    }
}
