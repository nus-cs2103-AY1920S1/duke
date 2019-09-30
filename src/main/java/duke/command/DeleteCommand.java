package duke.command;

import duke.dukeexception.DukeException;
import duke.task.Task;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] msg) {
        super(msg);
    }
    /**
     * Check is the user input format correct.
     * If is correct delete the task from taskList and the storage.
     * @param tasks  TaskList
     * @param ui    UiText
     * @param storage   Storage
     * @throws DukeException invalid input
     */

    @Override
    public String execute(TaskList tasks, UiText ui, Storage storage) throws DukeException {
        if (super.command.length != 2) {
            throw new DukeException("\u1F65 OOPS!! The format of the input is wrong");
        }
        assert super.command.length == 2;
        try {
            int index = Integer.parseInt(super.command[1].trim());
            if (index > tasks.getList().size()) {
                throw new DukeException(" OOPS! the Number you\'ve key in is to big");
            } else if (index < 1) {
                throw new DukeException("OOPS!! The number should be larger than 0");
            } else {
                assert index > 0 && index <= tasks.getList().size();
                Task tk = tasks.deleteFromList(index - 1);
                return UiText.deleteMsg(tk);
            }
        } catch (NumberFormatException ex) {
            throw new DukeException("OOPS! Invalid number as input");
        }
    }
}
