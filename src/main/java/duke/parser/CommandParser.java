package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RemindCommand;
import duke.command.ScheduleCommand;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidParameterException;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This is a parser used to parse a string representation of a command into a <code>Command</code> class object in
 * {@link duke.command.Command}. These <code>Command</code> can be executed in the <code>run</code> method of
 * {@link duke.main.Duke}.
 */
public class CommandParser {

    /**
     * Parses the string representation of a command into a <code>Command</code> class object.
     * @param fullCommand the full command to be parse
     * @return a command
     * @throws InvalidCommandException if a invalid command is entered by the user
     * @throws InvalidParameterException if the parameters passed into the command is invalid
     */
    public static Command parse(String fullCommand) throws InvalidCommandException, InvalidParameterException {
        String[] arr = fullCommand.split(" ");
        assert (arr.length > 0);
        String parameter = getParameter(arr);
        try {
            switch (getCommand(arr)) {
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(parameter);
            case "todo":
                return new AddTodoCommand(parameter);
            case "deadline":
                return new AddDeadlineCommand(parameter);
            case "event":
                return new AddEventCommand(parameter);
            case "delete":
                return new DeleteCommand(parameter);
            case "find":
                return new FindCommand(parameter);
            case "remind":
                return new RemindCommand(parameter);
            case "schedule":
                return new ScheduleCommand(parameter);
            case "bye":
                return new ExitCommand();
            default:
                throw new InvalidCommandException(getCommand(arr));
            }
        } catch (ArrayIndexOutOfBoundsException ioube) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Gets the command type from the array.
     * @param arr the array which contains the information
     * @return a string representation of the command type
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private static String getCommand(String[] arr) throws ArrayStoreException {
        return arr[0];
    }

    /**
     * Gets the parameter for the task from the array.
     * @param arr the array which contains the information
     * @return a string representation of the parameter for the command
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private static String getParameter(String[] arr) {
        return Arrays.stream(arr).skip(1).collect(Collectors.joining(" ")).trim();
    }

}
