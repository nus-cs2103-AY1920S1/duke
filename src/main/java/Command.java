import java.io.IOException;
import java.util.List;

public abstract class Command {
    CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException, IOException ;

    public static Command createAddCommandIfValid(String[] tokens) throws DukeException, IllegalArgumentException {
        List<String> validCommands = List.of("todo", "deadline", "event");

        if (!validCommands.contains(tokens[0])) {
            throw new DukeException("Command doesn't exist", DukeExceptionType.INVALIDCOMMAND);
        }
        Parser.checkValidLength(tokens);

        if (tokens[0].equals("todo")) {
            return new AddToDoCommand(tokens);
        } else if (tokens[0].equals("deadline")) {
            return AddDeadlineCommand.addDeadlineIfValid(tokens);
        } else if (tokens[0].equals("event")) {
            return AddEventCommand.addEventIfValid(tokens);
        } else {
            return new NullCommand();
        }
    }
}


