package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;
import duke.exception.DukeIllegalIndexException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Represents the "delete" command supported by Duke.
 */
public class DeleteCommand extends Command {

    /** Should contain the index of the Task to delete or the word "all." */
    private String detail;

    public DeleteCommand(String detail)  {
        this.detail = detail.trim();
    }

    /**
     * Deletes a <code>Task</code> from the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalIndexException When the index inputted is out of bounds or missing.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeIllegalIndexException, DukeIOException {
        try {
            int index = Integer.parseInt(this.detail);
            ui.printToUser(AutoResponse.DUKE_DELETE_TASK,
                           "  " + taskList.deleteTaskAt(index).getStatus(),
                           String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()));
        } catch (NumberFormatException e) {
            if (this.detail.isEmpty()) {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_MISSING_INDEX);
            }
            if (taskList.isEmpty()) {
                ui.printToUser(AutoResponse.DUKE_NO_TASKS);
            } else if (this.detail.equals("all")) {
                taskList.deleteAllTasks();
                ui.printToUser(AutoResponse.DUKE_DELETE_ALL_TASKS);
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }
        storage.saveTasks(taskList);
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
