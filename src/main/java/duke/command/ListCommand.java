package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

public class ListCommand extends Command {

    public ListCommand(String[] msg) {
        super(msg);
    }
    /**
     * Check is the user input format correct.
     * If is correct then list all the task in the taskList.
     * @param list  TaskList
     * @param ui    UiText
     * @param storage   Storage
     * @throws DukeException invalid input
     */

    @Override
    public String execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if (super.command.length != 1) {
            throw new DukeException("OOPS!! I\'m sorry, but I don\'t know what that means :-(");
        }
        return UiText.listingMsg(list.getList());
    }
}
