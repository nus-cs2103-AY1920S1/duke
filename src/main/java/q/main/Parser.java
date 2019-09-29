package q.main;

import q.command.ArchiveCommand;
import q.command.Command;
import q.command.DeadlineCommand;
import q.command.DeleteCommand;
import q.command.DoneCommand;
import q.command.EventCommand;
import q.command.ExitCommand;
import q.command.FindCommand;
import q.command.HelpCommand;
import q.command.ListCommand;
import q.command.TodoCommand;
import q.command.ViewArchiveCommand;
import q.error.QException;
import q.error.InsufficientArgumentError;
import q.error.MissingKeywordError;
import q.error.UnknownCommandError;

import java.util.Date;

/**
 * A parser for converting inputs to Commands.
 */
public class Parser {
    /**
     * Parses an input and converts it into a command object.
     *
     * @param input A user inputted command String.
     * @return A command representing the input.
     * @throws QException When there is error in input.
     */
    public static Command parse(String input) throws QException {
        String[] currentInputArray = input.split("\\s");
        switch (currentInputArray[0]) {
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "archive":
            return new ArchiveCommand();
        case "view":
            return new ViewArchiveCommand();
        case "done":
            return new DoneCommand(checkNumberInput(currentInputArray));
        case "delete":
            return new DeleteCommand(checkNumberInput(currentInputArray));
        case "todo":
            checkNumberOfArguments(currentInputArray);
            return new TodoCommand(input.substring(5));
        case "deadline":
            checkNumberOfArguments(currentInputArray);
            String[] deadlineArr = checkDateInput(input, MissingKeywordError.Keyword.BY, 8);
            Date deadlineDate = DateTimeParser.parse(deadlineArr[1]);
            return new DeadlineCommand(deadlineArr[0].substring(8 + 1), deadlineDate);
        case "event":
            checkNumberOfArguments(currentInputArray);
            String[] eventArr = checkDateInput(input, MissingKeywordError.Keyword.AT, 5);
            Date eventDate = DateTimeParser.parse(eventArr[1]);
            return new EventCommand(eventArr[0].substring(5 + 1), eventDate);
        case "bye":
            return new ExitCommand();
        case "find":
            checkNumberOfArguments(currentInputArray);
            return new FindCommand(currentInputArray[1]);
        default:
            throw new UnknownCommandError();
        }
    }

    /**
     * Takes in an input String array and gets the number input.
     *
     * @param arr An input String array.
     * @return The number input.
     * @throws MissingKeywordError When there is no number keyword.
     */
    private static int checkNumberInput(String[] arr) throws MissingKeywordError {
        if (arr.length < 2) {
            throw new MissingKeywordError(MissingKeywordError.Keyword.NUMBER);
        }
        if (!arr[1].matches("-?\\d+")) {
            throw new MissingKeywordError(MissingKeywordError.Keyword.NUMBER);
        }
        return Integer.parseInt(arr[1]) - 1;
    }

    /**
     * Helper function for checking number of arguments.
     *
     * @param arr Input array.
     * @throws InsufficientArgumentError If insufficient arguments are provided.
     */
    private static void checkNumberOfArguments(String[] arr) throws InsufficientArgumentError {
        if (arr.length < 2) {
            throw new InsufficientArgumentError();
        }
    }

    /**
     * Helper function for checking date input.
     *
     * @param input         The String input.
     * @param keyword       The keyword of the date.
     * @param commandLength The length of the command e.g. 8 for deadline, 5 for event.
     * @return A String array with description and date.
     * @throws MissingKeywordError       If a keyword is missing.
     * @throws InsufficientArgumentError If there are missing arguments.
     */
    private static String[] checkDateInput(String input, MissingKeywordError.Keyword keyword, int commandLength)
            throws MissingKeywordError, InsufficientArgumentError {
        assert MissingKeywordError.Keyword.getKeywordString(keyword) != null;
        if (!input.contains(MissingKeywordError.Keyword.getKeywordString(keyword))) {
            throw new MissingKeywordError(MissingKeywordError.Keyword.BY);
        }
        String[] arr = input.split(MissingKeywordError.Keyword.getKeywordString(keyword));
        if (arr.length < 2 || arr[0].length() < commandLength + 2) {
            throw new InsufficientArgumentError();
        }
        return arr;
    }
}
