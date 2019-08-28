package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * Command Class for delete tasks
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand Object
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes deletion of task from task list
     * @param taskList list of tasks
     * @param storage storage to store inside hard disk
     * @param ui ui for user interaction
     * @return boolean indication of successful or unsuccessful running of command
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        Task deletedTask = taskList.deleteAt(index);

        ui.printRemovedAcknowledgement(deletedTask, taskList.getSize());

        storage.save(taskList);

        return true;
    }
}
