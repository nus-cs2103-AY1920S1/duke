package duke.command;

import duke.dukeexception.DukeException;
import duke.task.Task;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

public class DoneCommand extends Command {
    public DoneCommand(String[] msg) {
        super(msg);
    }
    /**
     * Check is the user input format correct.
     * If is correct then mark the task as done and update the taskList and the storage
     * @param tasks  TaskList
     * @param ui    UiText
     * @param storage   Storage
     * @throws DukeException invalid input
     */

    @Override
    public String execute(TaskList tasks, UiText ui, Storage storage) throws DukeException {
        if (super.command.length != 2) {
            throw new DukeException("OOPS!! The format of the input is wrong");
        }
        assert super.command.length == 2 : "command wrong format";
        try {
            int index = Integer.parseInt(super.command[1].trim());
            if (index > tasks.getList().size()) {
                throw new DukeException("OOPS! the Number you\'ve key in is to big");
            } else if (index < 1) {
                throw new DukeException("OOPS!! The number should be larger than 0");
            } else {
                assert index > 0 && index <= tasks.getList().size();
                Task tk = tasks.getList().get(index - 1);
                if (tk.getStatusBit() == 1) {
                    throw new DukeException("The task was already completed!");
                }
                assert tk.getStatusBit() == 0 : "the task was already completed!";
                tk.markAsDone();
                //marked msg
                return UiText.markedMsg(tk);
            }
        } catch (NumberFormatException ex) {
            throw new DukeException("OOPS! Invalid number as input");
        }
    }
}
