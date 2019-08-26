package duke;

import java.text.ParseException;

import duke.util.DateUtil;
//CHECKSTYLE:OFF
import duke.command.*;
import duke.error.*;
//CHECKSTYLE:ON

public class Parser {
    /**
     * Handles the various commands.
     * @param command String
     * @return boolean
     */
    public static Command parse(String command) throws DukeException, ParseException { 
        String keyword = command.split(" ")[0].trim();
        switch (keyword) {
        case "bye": {
            return new ByeCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "done": {
            return parseDoneCommand(command);
        }
        case "todo": {
            return parseTodoCommand(command);
        }
        case "deadline": {
            return parseDeadlineCommand(command);
        }
        case "event": {
            return parseEventCommand(command);
        }
        case "delete": {
            return parseDeleteCommand(command);
        }
        case "find": {
            return parseFindCommand(command);
        }
        default:
            throw new InvalidCommandException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parse Done command.
     * @param command String
     * @throws InvalidCommandException if the Done command is not correct
     */
    private static Command parseDoneCommand(String command) throws InvalidCommandException {
        String[] doneArr = command.split(" ");
        if (doneArr.length != 2) {
            throw new InvalidCommandException("\u2639 OOPS!!! Done command should only have a valid index");
        }
        int indexToEdit = Integer.parseInt(doneArr[1].trim());
        return new DoneCommand(indexToEdit);
    }

    /**
     * Parse Delete command.
     * @param command String
     * @throws InvalidCommandException if the arguments provided are invalid
     */
    private static Command parseDeleteCommand(String command) throws InvalidCommandException {
        String[] deleteArr = command.split(" ");
        if (deleteArr.length != 2) {
            throw new InvalidCommandException("\u2639 OOPS!!! Done command should only have a valid index");
        }
        int indexToEdit = Integer.parseInt(deleteArr[1]);
        return new DeleteCommand(indexToEdit);
    }

    /**
     * Parse Deadline command.
     * @param command String
     * @throws InvalidTaskArgumentException if Deadline arguments are invalid
     * @throws ParseException if it fails to parse the date
     */
    private static Command parseDeadlineCommand(String command) 
        throws InvalidTaskArgumentException, ParseException {
        String[] commandArr = command.replace("deadline", "").trim().split("/by ");
        if (commandArr.length != 2) {
            throw new InvalidTaskArgumentException("\u2639 OOPS!!! Deadline must have a description and date");
        }
        return new DeadlineCommand(commandArr[0].trim(), DateUtil.parseStringToDate(commandArr[1].trim()));
    }

    /**
     * Parse Event command.
     * @param command String
     * @throws InvalidTaskArgumentException if Event arguments are invalid
     * @throws ParseException if it fails to parse the date
     */
    private static Command parseEventCommand(String command) 
        throws InvalidTaskArgumentException, ParseException {
        String[] commandArr = command.replace("event", "").trim().split("/at ");
        if (commandArr.length != 2) {
            throw new InvalidTaskArgumentException("\u2639 OOPS!!! Event must have a description and date");
        }
        return new EventCommand(commandArr[0].trim(), DateUtil.parseStringToDate(commandArr[1].trim()));
    }

    /**
     * Parse ToDo command.
     * @param command String
     * @throws InvalidTaskArgumentException if ToDo arguments are invalid
     */
    private static Command parseTodoCommand(String command) throws InvalidTaskArgumentException {
        String name = command.replace("todo", "").trim();
        if ("".equals(name)) {
            throw new InvalidTaskArgumentException("\u2639 OOPS!!! The description of a todo cannot be empty."); 
        }
        return new ToDoCommand(name);
    }

    /**
     * Parse Find Command.
     * @param command String
     * @throws InvalidTaskArgumentException if arguments are invalid
     */
    private static Command parseFindCommand(String command) throws InvalidTaskArgumentException {
        String keyword = command.replace("find", "").trim();
        if ("".equals(keyword)) {
            throw new InvalidTaskArgumentException("\u2639 OOPS!!! The description of a find cannot be empty."); 
        }
        return new FindCommand(keyword);
    }
}
