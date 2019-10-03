package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command to display the full list of recorded tasks to user.
 */
public class ListCommand extends Command {


    /**
     * Constructor of ListCommand.
     */
    public ListCommand() {
        super.type = FullCommand.LIST;
    }

    /**
     * Used to check if command is an ExitCommand.
     * @return ConstructorCommand is not ExitCommand
     */
    public boolean isExit() {
        assert(!super.type.getName().equals("bye"));
        return false;
    }

    /**
     * Passes TaskList as an argument for ui.readList to display to user.
     * @param tasks current TaskList object used in this instance of Duke..
     * @param ui Instance of user interface to print feedback to user.
     * @param storage updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException should not be thrown for this Command subclass.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.readList(tasks);
    }
}
