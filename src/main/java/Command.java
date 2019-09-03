import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

abstract class Command {
    /*
    1. create command with parser
    2. abstract execute(tasks, ui, storage)
    3. boolean isExit()
     */
    protected String command;
    protected String commandDetails;
    protected String INDENT;

    public Command(String command, String commandDetails, String INDENT) {
        this.command = command;
        this.commandDetails = commandDetails;
        this.INDENT = INDENT;
    }

    public boolean isExit() {
        return command.equals("bye");
    }

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
