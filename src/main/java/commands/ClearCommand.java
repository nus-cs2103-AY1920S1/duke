package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ClearCommand is a class that enables
 * the user to clear the pane of dialog-boxes.
 */
public class ClearCommand extends Command {

    /**
     * Constructor for ClearCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public ClearCommand(String[] commandArr) {
        super(commandArr);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getClearedMsg();
    }
}
