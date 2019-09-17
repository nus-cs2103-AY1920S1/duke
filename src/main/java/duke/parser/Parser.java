package duke.parser;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.Command;
import duke.dukeexception.DukeException;
import static duke.dukeexception.DukeException.*;

/**
 * Class to handle parsing of user input.
 */
public class Parser {

    /**
     * Returns the command that is intended by the user's input String.
     *
     * @param toParse User input String to be parsed.
     * @return Command that is to be executed.
     * @throws DukeException if input String toParse is not recognized
     *     or in the wrong format.
     */
    public static Command parse(String toParse) throws DukeException {
        String[] userInputTokens = toParse.split(" ");
        String commandKeyword = parseAndGetCommandKeyword(userInputTokens);
        Command commandToReturn;
        switch (commandKeyword) {
        case "list":
            commandToReturn = parseAndGetListCommand();
            break;
        case "done":
            commandToReturn = parseAndGetDoneCommand(userInputTokens);
            break;
        case "delete":
            commandToReturn = parseAndGetDeleteCommand(userInputTokens);
            break;
        case "todo":
            commandToReturn = parseAndGetTodoCommand(userInputTokens);
            break;
        case "deadline":
            commandToReturn = parseAndGetDeadlineCommand(toParse);
            break;
        case "event":
            commandToReturn = parseAndGetEventCommand(toParse);
            break;
        case "bye":
            commandToReturn = parseAndGetByeCommand();
            break;
        case "find":
            commandToReturn = parseAndGetFindCommand(toParse);
            break;
        default:
            //unrecognized command
            throw new DukeException(UNRECOGNIZED_COMMAND_ERROR);
        }
        return commandToReturn;
    }

    public static String parseAndGetLastOpenedFile(String toParse) throws DukeException {
        try {
            String lastOpenedFilePath = toParse.split("\\|")[1];
            return lastOpenedFilePath;
        } catch (Exception e) {
            throw new DukeException(METADATA_ERROR);
        }
    }

    private static String parseAndGetCommandKeyword(String[] tokens) {
        return tokens[0];
    }

    private static DoneCommand parseAndGetDoneCommand(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException(DONE_ERROR);
        }
        int toComplete = Integer.parseInt(tokens[1]) - 1;
        return new DoneCommand(toComplete);
    }

    private static DeleteCommand parseAndGetDeleteCommand(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException(DELETE_ERROR);
        }
        int toDelete = Integer.parseInt(tokens[1]) - 1;
        return new DeleteCommand(toDelete);
    }

    private static TodoCommand parseAndGetTodoCommand(String[] tokens) throws DukeException {
        String toAdd = "";
        if (tokens.length == 1) {
            throw new DukeException(EMPTY_TODO_ERROR);
        }
        for (int j = 1; j < tokens.length; j++) {
            toAdd = toAdd + tokens[j] + " ";
        }
        return new TodoCommand(toAdd.trim());
    }

    private static DeadlineCommand parseAndGetDeadlineCommand(String toParse) throws DukeException {
        String[] tokens = toParse.split(" ");
        boolean dateFlag = false;
        String toAdd = "";
        String dateString = "";
        String timeString = "";

        if (tokens.length == 1) {
            throw new DukeException(EMPTY_DEADLINE_ERROR);
        }

        assert toParse.split("/by").length > 1: "Deadline command did not use /by delimiter";

        if(!toParse.contains("/by"))
            throw new DukeException(DATETIME_ERROR);
        if (toParse.split("/by")[1].trim().split(" ").length != 2) {
            throw new DukeException(DATETIME_ERROR);
        }

        for (int m = 1; m < tokens.length; m++) {
            if (tokens[m].equals("/by")) {
                dateFlag = true;
            } else {
                if (dateFlag == false) {
                    toAdd = toAdd + tokens[m] + " ";
                } else {
                    if (m == tokens.length - 1) {
                        timeString = tokens[m];
                    } else {
                        dateString = tokens[m];
                    }
                }
            }
        }
        return new DeadlineCommand(toAdd.trim(), dateString.trim(), timeString.trim());
    }

    private static EventCommand parseAndGetEventCommand(String toParse) throws DukeException {
        String[] tokens = toParse.split(" ");
        String toAdd = "";
        String dateString = "";
        String timeString = "";
        boolean timeFlag = false;

        if (tokens.length == 1) {
            throw new DukeException(EMPTY_EVENT_ERROR);
        }

        assert toParse.split("/at").length > 1: "Event command did not use /at delimiter";
        if(!toParse.contains("/at"))
            throw new DukeException(DATETIME_ERROR);
        //Check if both date and time are specified

        if(toParse.split("/at").length < 2) {
            throw new DukeException(DATETIME_ERROR);
        }

        if (toParse.split("/at")[1].trim().split(" ").length != 2) {
            throw new DukeException(DATETIME_ERROR);
        }
        for (int z = 1; z < tokens.length; z++) {
            if (tokens[z].equals("/at")) {
                timeFlag = true;
            } else {
                if (timeFlag == false) {
                    toAdd = toAdd + tokens[z] + " ";
                } else {
                    if (z == tokens.length - 1) {
                        timeString = tokens[z];
                    } else {
                        dateString = tokens[z];
                    }
                }
            }
        }
        return new EventCommand(toAdd.trim(), dateString.trim(), timeString.trim());
    }

    private static ByeCommand parseAndGetByeCommand() {
        return new ByeCommand();
    }

    private static FindCommand parseAndGetFindCommand(String toParse) throws DukeException {
        if (toParse.split(" ").length < 2) {
            throw new DukeException(FIND_KEYWORD_ERROR);
        }

        String keyword = toParse.split("find")[1].strip();
        return new FindCommand(keyword);
    }

    private static ListCommand parseAndGetListCommand() {
        return new ListCommand();
    }
}
