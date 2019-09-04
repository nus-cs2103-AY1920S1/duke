/**
 * An abstract class command, created from the Parser.
 * Contains Strings to store the command and type of command.
 */

public abstract class Command {
    protected String command;
    protected String type;
    protected static boolean isExit = false;

    public Command(String type, String fullCommand) {
        this.type = type;
        this.command = fullCommand;
    }

    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {

    }


}
