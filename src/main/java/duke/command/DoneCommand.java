package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

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
     * Marks a <code>Task</code> in the <code>TaskList</code> as done.
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
            taskList.markAsDoneTaskAt(index);
            ui.printToUser(AutoResponse.DUKE_MARK_AS_DONE, "  " + taskList.getTaskAt(index).getStatus());
        } catch (NumberFormatException e) {
            if (this.detail.isEmpty()) {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_MISSING_INDEX);
            }

            if (taskList.isEmpty()) {
                ui.printToUser(AutoResponse.DUKE_NO_TASKS);
            } else if (this.detail.equals("all")) {
                taskList.markAsDoneAllTasks();
                String[] lines = taskList.listAll();
                lines[0] = AutoResponse.DUKE_DONE_ALL_TASKS;
                ui.printToUser(lines);
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }
        storage.saveTasks(taskList);
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
