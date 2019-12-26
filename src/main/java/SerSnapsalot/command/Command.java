package SerSnapsalot.command;

import SerSnapsalot.ui.Ui;
import SerSnapsalot.taskList.TaskList;
import SerSnapsalot.storage.Storage;
import SerSnapsalot.exception.DukeException;
/**
 * An abstract class command, created from the parser.Parser.
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

    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        return "";
    }


}
