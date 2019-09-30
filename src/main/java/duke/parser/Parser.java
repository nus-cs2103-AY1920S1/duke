package duke.parser;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.SendTasksCommand;
import duke.command.DoneTaskCommand;
import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.DeleteTaskCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.MissingCommandException;
import duke.exception.MissingDateTimeException;
import duke.exception.MissingDeadlineException;
import duke.exception.MissingEventException;
import duke.exception.MissingKeywordException;
import duke.exception.MissingTaskIndexException;
import duke.exception.MissingTodoException;
import duke.exception.MissingStartException;
import duke.exception.MissingEndException;

/**
 * Deals with making sense of the user command.
 * Determines which command to be executed by Duke.
 */
public class Parser {

    String fullCommand;

    private static Command command;

    public Parser(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Recognises certain user inputs as a valid Duke command to be executed.
     *
     * @param fullCommand raw user input.
     * @return a suitable command.
     * @throws DukeException if incomplete or invalid user input given.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] splitStr = fullCommand.split(" ", 2);
        switch (splitStr[0]) {
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new SendTasksCommand();
            break;
        case "done":
            checkString(splitStr);
            command = new DoneTaskCommand(splitStr[1].trim());
            break;
        case "todo":
            checkString(splitStr);
            command = new AddTodoCommand(splitStr[1].trim());
            break;
        case "deadline":
            checkString(splitStr);
            String item = splitStr[1].trim();
            if (item.startsWith("/by")) {
                throw new MissingDeadlineException();
            } else if (item.endsWith("/by") || (!item.contains("/by"))) {
                throw new MissingDateTimeException();
            } else {
                String[] data = item.split("/by", 2);
                command = new AddDeadlineCommand(data[0].trim(), data[1].trim());
            }
            break;
        case "event":
            checkString(splitStr);
            String event = splitStr[1].trim();
            if (event.startsWith("/at")) {
                throw new MissingEventException();
            } else if (event.endsWith("/at") || (!event.contains("/at"))) {
                throw new MissingDateTimeException();
            } else {
                String[] data = event.split("/at", 2);
                String duration = data[1].trim();
                if (duration.startsWith("/to")) {
                    throw new MissingStartException();
                } else if (duration.endsWith("/to") || (!duration.contains("/to"))) {
                    throw new MissingEndException();
                } else {
                    String[] times = duration.split("/to");
                    command = new AddEventCommand(data[0].trim(), times[0].trim(), times[1].trim());
                }
            }
            break;
        case "delete":
            checkString(splitStr);
            command = new DeleteTaskCommand(splitStr[1].trim());
            break;
        case "find":
            checkString(splitStr);
            command = new FindCommand(splitStr[1].trim());
            break;
        case "":
            throw new MissingCommandException();
        default:
            throw new InvalidCommandException();
        }
        return command;
    }

    /**
     * Method to check number of words in string parsed, and throw the correct exception where appropriate.
     *
     * @param splitStr string array being checked.
     * @throws DukeException correct exception to be thrown.
     */
    private static void checkString(String[] splitStr) throws DukeException {
        // if less than 2 words
        if (splitStr.length < 2) {
            switch (splitStr[0]) {
            case "done":
            case "delete":
                throw new MissingTaskIndexException();
            case "todo":
                throw new MissingTodoException();
            case "deadline":
                throw new MissingDeadlineException();
            case "event":
                throw new MissingEventException();
            case "find":
                throw new MissingKeywordException();
            default:
                assert false : splitStr[0];
            }
        }
    }

}
