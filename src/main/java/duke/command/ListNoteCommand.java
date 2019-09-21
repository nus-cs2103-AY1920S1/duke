package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListNoteCommand extends Command {
    /**
     * Prints all Note names stored in the hard drive.
     *
     * @param taskList The TaskList object passed from Duke.
     * @param ui The Ui object passed from Duke.
     * @param storage The Storage object passed from Duke.
     * @return The response String.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showNoteList();
    }
}
