package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;
import duke.exception.IncorrectFormatException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingFieldException;
import duke.exception.UnknownCommandException;

public class Parser {

    /**
     * Parses any user inputs and determines the type of command that was entered.
     *
     * @param fullCommand The command input by user.
     * @return The type of command entered.
     * @throws DukeException In the event that a command has an incorrect format/ empty description.
     */
    public static Command parse(String fullCommand) throws DukeException {
        //Parse the string to check which command it is referring to
        String[] details = fullCommand.trim().split("\\s+");
        try {
            switch (details[0]) {
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(details[1]);
            case "sort":
                return new SortCommand(details[1]);
            case "done":
                return new DoneCommand(Integer.parseInt(details[1]) - 1);
            case "delete":
                return new DeleteCommand(Integer.parseInt(details[1]) - 1);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(details[0], getNameAndDate(fullCommand));
            case "bye":
                return new ExitCommand();
            default:
                throw new UnknownCommandException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // If the command misses a description entirely
            switch (details[0]) {
            case "todo":
            case "event":
            case "deadline":
                throw new MissingDescriptionException(details[0]);
            case "done":
            case "delete":
                throw new MissingFieldException("task index");
            case "find":
                throw new MissingFieldException("keyword to find");
            default:
                throw new IncorrectFormatException();
            }
        } catch (NumberFormatException e) {
            // If the delete/done commands are followed by strings instead of a number index
            throw new IncorrectFormatException(details[0], "should be followed by a number index not a string");
        }
    }

    /**
     * Gets task name and date separately from full command.
     *
     * @param fullCommand The command input by user.
     * @return The string array containing the task name and task date parsed from full command.
     * @throws DukeException In the event that a command is missing a task name.
     */
    static String[] getNameAndDate(String fullCommand) throws DukeException {
        String taskType = fullCommand.trim().split("\\s+")[0]; //e.g. deadline
        String nameAndDate = fullCommand.split(taskType)[1].trim(); //e.g. do homework /by tomorrow
        String[] details;
        try {
            switch (taskType) {
            case "deadline":
                return parseDeadlineDetails(taskType, nameAndDate);
            case "event":
                return parseEventDetails(taskType, nameAndDate);
            default:
                return parseToDoDetails(taskType, nameAndDate);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncorrectFormatException(taskType);
        }
    }

    static String[] parseToDoDetails(String taskType, String nameAndDate) throws DukeException {
        if (nameAndDate.length() < 1) {
            throw new MissingDescriptionException(taskType);
        }
        return new String[] {nameAndDate};
    }


    static String[] parseDeadlineDetails(String taskType, String nameAndDate) throws DukeException {
        String[] details;
        details = nameAndDate.split("/by");
        if (details.length == 0) { //e.g. deadline /by
            throw new MissingFieldException("<$task_name> and a <$task_date>");
        } else if (details[0].isBlank()) { //e.g. deadline (space)
            throw new MissingDescriptionException(taskType);
        } else if (details.length == 1) { //e.g. deadline do homework
            throw new MissingFieldException("<$task_date>");
        }
        return details;
    }

    private static String[] parseEventDetails(String taskType, String nameAndDate) throws DukeException {
        String[] details;
        details = nameAndDate.split("/at");
        if (details.length == 0) {
            throw new MissingFieldException("<$task_name>, and <$start_date> - <$end_date>");
        } else if (details[0].isBlank()) {
            throw new MissingDescriptionException(taskType);
        } else if (details[1].isBlank() || !details[1].contains("-")) {
            throw new MissingFieldException("<$end_date>");
        }
        return details;
    }
}
