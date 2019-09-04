package dukegui.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;

/**
 * Represents the "done" command supported by Duke.
 */
public class DoneCommand extends Command {

    /** Should contain the index of the Task to mark as done or the word "all." */
    private String detail;

    public DoneCommand(String detail) {
        this.detail = detail.trim();
    }

    /**
     * Returns the response of marking a <code>Task</code> in the <code>TaskList</code> as done.
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
            taskList.markAsDoneTaskAt(index);
            response = new StringBuilder(AutoResponse.DUKE_MARK_AS_DONE)
                    .append("\n")
                    .append("  ")
                    .append(taskList.getTaskAt(index).getStatus())
                    .toString();
        } catch (NumberFormatException e) {
            if (this.detail.isEmpty()) {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_MISSING_INDEX);
            }
            if (taskList.isEmpty()) {
                response = AutoResponse.DUKE_NO_TASKS;
            } else if (this.detail.equals("all")) {
                taskList.markAsDoneAllTasks();
                String[] lines = taskList.listAll();
                StringBuilder sb = new StringBuilder(AutoResponse.DUKE_MARK_AS_DONE);
                for (int i = 1; i < lines.length; i++) {
                    sb.append(lines[i]);
                    sb.append("\n");
                }
                response = sb.toString();
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }
        storage.saveTasks(taskList);
        return response;
    }

    /**
     * Returns false.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
