package dukegui.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.AutoResponse;

import duke.task.Task;

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
     * Returns the result of deleting a <code>Task</code> from the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalIndexException When the index inputted is out of bounds.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, Storage storage)
            throws DukeIllegalIndexException, DukeIOException {
        String response;
        try {
            int index = Integer.parseInt(this.detail);
            Task task = taskList.deleteTaskAt(index);
            response = new StringBuilder(AutoResponse.DUKE_DELETE_TASK)
                    .append("\n")
                    .append("  ")
                    .append(task.getStatus())
                    .append("\n")
                    .append(String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()))
                    .toString();
        } catch (NumberFormatException e) {
            if (this.detail.isEmpty()) {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_MISSING_INDEX);
            }
            if (taskList.isEmpty()) {
                response = AutoResponse.DUKE_NO_TASKS;
            } else if (this.detail.equals("all")) {
                taskList.deleteAllTasks();
                response = AutoResponse.DUKE_DELETE_ALL_TASKS;
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }
        storage.saveTasks(taskList);
        return response;
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
