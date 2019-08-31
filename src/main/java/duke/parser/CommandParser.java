package duke.parser;

import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.InvalidCommandException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CommandParser {

    public static Command parse (String fullCommand) {
        String[] arr = fullCommand.split(" ");
        String parameter = Arrays.stream(arr).skip(1).collect(Collectors.joining(" ")).trim();
        try {
            switch (arr[0]) {
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
            case "bye":
                return new ExitCommand();
            default:
                throw new InvalidCommandException(arr[0]);
            }
        } catch (ArrayIndexOutOfBoundsException ioube) {
            throw new InvalidCommandException();
        }
    }
}
