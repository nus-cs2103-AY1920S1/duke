import java.io.IOException;

public abstract class Command {
    CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException, IOException;

}
