package duke.command;

import duke.storage.Storage;
import duke.logic.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Delete Command.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for DeleteCommand object.
     * @param num is the taskNumber that needs to be deleted.
     */
    public DeleteCommand(int num) {
        super(false);
        this.index = num - 1;

    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task t = taskList.getTask(index);
        taskList.delete(index);

        storage.writeListToFile(taskList);
        return ui.showDeletedTask(taskList,t);
    }
}
