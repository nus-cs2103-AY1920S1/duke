package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RemindCommand;
import duke.exception.DukeException;
import duke.exception.DukeIndexOutOfBoundsException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeUnknownInputException;
import duke.task.TaskType;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Logger;

import static duke.task.TaskType.TODO;
import static duke.task.TaskType.DEADLINE;
import static duke.task.TaskType.EVENT;
import static duke.task.TaskType.ALL;
import static java.util.logging.Level.FINE;
import static java.util.logging.Level.INFO;

/**
 * Deals with making sense of commands.
 */
public class Parser {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final Locale LOCALE = Locale.ENGLISH;
    public static final String DEADLINE_PARSE_PATTERN = "ddMMyy HHmm";
    public static final String DEADLINE_FORMAT_PATTERN = "dd MMMM yyyy, hh:mma, O";

    /**
     * Parses the full command and returns the corresponding Command instance.
     *
     * @param command the full command to parse.
     * @return the command corresponding to the input.
     * @throws DukeException an exception generated when parsing the input.
     */
    public static Command parse(String command) throws DukeException {
        // remove trailing/leading whitespace and split by whitespace(s)
        command = command.strip();
        String[] commands = command.split("[ ]+");
        String[] args = Arrays.copyOfRange(commands, 1, commands.length);

        assert args.length == commands.length - 1 : "Incorrect array copy";

        switch (commands[0]) {
        case "todo":
            return parseTodoCommand(args);
        case "event":
            return parseEventCommand(args);
        case "deadline":
            return parseDeadlineCommand(args);
        case "find":
            return parseFindCommand(args);
        case "done":
            return parseDoneCommand(args);
        case "delete":
            return parseDeleteCommand(args);
        case "list":
            return parseListCommand(args);
        case "remindme":
            return parseRemindCommand(args);
        case "bye":
            return parseBye(args);
        default:
            throw new DukeMissingDescriptionException("Hmm... I'm sorry, but I don't know what that means...");
        }
    }

    /*
    Business logic for each command. Refactor concept: Extract Method
     */
    private static Command parseTodoCommand(String[] args) throws DukeMissingDescriptionException {
        logger.log(INFO, "Parse TODO command: {0}", new Object[]{Arrays.toString(args)});

        if (args.length == 0) {
            throw new DukeMissingDescriptionException("Hmm... I need to know your to-do description.");
        }
        return new AddCommand(TODO, args, false);
    }

    private static Command parseEventCommand(String[] args) throws DukeUnknownInputException {
        logger.log(INFO, "Parse EVENT command: {0}", new Object[]{Arrays.toString(args)});

        String[] eventArgs = String.join(" ", args).split(" /at ");
        if (eventArgs.length != 2) {
            throw new DukeUnknownInputException("Hmm... Incorrect argument count for event!");
        }

        return new AddCommand(EVENT, args, false);
    }

    private static Command parseDeadlineCommand(String[] args) throws DukeUnknownInputException {
        logger.log(INFO, "Parse DEADLINE command: {0}", new Object[]{Arrays.toString(args)});

        String[] deadlineArgs = String.join(" ", args).split(" /by ");
        if (deadlineArgs.length != 2) {
            throw new DukeUnknownInputException("Hmm... Incorrect argument count for deadline!");
        }
        // enforce deadline format ddMMyy HHmm
        parseDeadline(deadlineArgs[1]);

        return new AddCommand(DEADLINE, args, false);
    }

    private static Command parseFindCommand(String[] args) throws DukeMissingDescriptionException {
        logger.log(INFO, "Parse FIND command: {0}", new Object[]{Arrays.toString(args)});

        if (args.length == 0) {
            throw new DukeMissingDescriptionException("Hmm... I need to know what you're searching for.");
        }

        String keyword = args[0].strip();
        return new FindCommand(keyword, false);
    }

    private static Command parseDoneCommand(String[] args) throws DukeIndexOutOfBoundsException {
        logger.log(INFO, "Parse DONE command: {0}", new Object[]{Arrays.toString(args)});

        int doneIdx = Integer.valueOf(args[0]);
        if (doneIdx < 0) {
            throw new DukeIndexOutOfBoundsException("Hmm... that task isn't in the list.");
        }

        return new DoneCommand(doneIdx, false);
    }

    private static Command parseDeleteCommand(String[] args) throws DukeIndexOutOfBoundsException {
        logger.log(INFO, "Parse DELETE command: {0}", new Object[]{Arrays.toString(args)});

        int deleteIdx = Integer.valueOf(args[0]);
        if (deleteIdx < 0) {
            throw new DukeIndexOutOfBoundsException("Hmm... that task isn't in the list.");
        }

        return new DeleteCommand(deleteIdx, false);
    }

    private static Command parseListCommand(String[] args) {
        logger.log(INFO, "Parse LIST command: {0}", new Object[]{Arrays.toString(args)});

        return new ListCommand(false);
    }

    private static Command parseRemindCommand(String[] args) {
        logger.log(INFO, "Parse REMIND command: {0}", new Object[]{Arrays.toString(args)});

        if (args.length != 1) {
            throw new DukeUnknownInputException("Hmm... would you like a reminder on your event or deadline tasks?");
        }

        TaskType type;
        if (args[0].equals("deadline")) {
            type = DEADLINE;
        } else if (args[0].equals("event")) {
            type = EVENT;
        } else if (args[0].equals("all")) {
            type = ALL;
        } else {
            throw new DukeUnknownInputException("Hmm... I don't know those arguments. Do you mean deadline or event?");
        }

        return new RemindCommand(type, false);
    }

    private static Command parseBye(String[] args) {
        logger.log(INFO, "Parse BYE command: {0}", new Object[]{Arrays.toString(args)});

        return new ExitCommand(true);
    }

    /**
     * Business logic for date and time parsing. Code adapted from duke/WeomuCat
     */

    /**
     * Creates a DateTime object with format specified by the format pattern.
     *
     * @param in the input string, which must follow the parse pattern.
     * @param type the task type in which are parsing for, which differs between Event types.
     * @return the ZonedDateTime object.
     * @throws DukeUnknownInputException if the input string is of invalid format, or an invalid task type is passed.
     */
    public static ZonedDateTime parseDateTime(String in, TaskType type) throws DukeUnknownInputException {
        // switch allows us to parse dateTimes differently by task type. see parseDeadline for exampe.
        switch (type) {
        case DEADLINE:
            return parseDeadline(in);
        default:
            throw new DukeUnknownInputException("Hmm... please pass deadline task types instead.");
        }
    }

    private static ZonedDateTime parseDeadline(String by) throws DukeUnknownInputException {
        logger.log(FINE, "Parse deadline to ZonedDateTime: {0}", new Object[]{by});

        try {
            DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern(DEADLINE_PARSE_PATTERN, LOCALE)
                    .withZone(ZoneId.systemDefault());
            ZonedDateTime out = ZonedDateTime.parse(by, parseFormatter);

            logger.log(FINE, "Parsed to ZonedDateTime successfully: {0}", new Object[]{out});

            return out;
        } catch (DateTimeParseException e) {
            throw new DukeUnknownInputException(
                    String.format("Hmm... that date format's wrong. Follow this format: %s", DEADLINE_PARSE_PATTERN));
        }
    }
}
