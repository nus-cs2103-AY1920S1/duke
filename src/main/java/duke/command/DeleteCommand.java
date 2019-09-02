package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Represents the "delete" command supported by Duke.
 */
public class DeleteCommand extends Command {

    /** {@value DUKE_DELETE_TASK} */
    private static final String DUKE_DELETE_TASK = "Noted. I've removed this task:";
    /** {@value DUKE_DELETE_ALL_TASKS} */
    private static final String DUKE_DELETE_ALL_TASKS = "Noted. I've removed all tasks.";
    /** {@value DUKE_NUMBER_OF_TASKS} */
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";

    /** {@value ERROR_ILLEGAL_INDEX} */
    private static final String ERROR_ILLEGAL_INDEX = "☹ OOPS!!! The index must be a number "
            + "separated by one whitespace.";

    /** Should contain the index of the Task to delete or the word "all." */
    private String detail;

    public DeleteCommand(String detail)  {
        this.detail = detail;
    }

    /**
     * Deletes a <code>Task</code> from the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalIndexException When the index inputted is out of bounds.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeIllegalIndexException, DukeIOException {
        try {
            int index = Integer.parseInt(this.detail);
            ui.printToUser(DUKE_DELETE_TASK,
                           "  " + taskList.deleteTaskAt(index).getStatus(),
                           String.format(DUKE_NUMBER_OF_TASKS, taskList.getSize()));
        } catch (NumberFormatException e) {
            if (this.detail.equals("all")) {
                taskList.deleteAllTasks();
                ui.printToUser(DUKE_DELETE_ALL_TASKS);
            } else {
                throw new DukeIllegalIndexException(ERROR_ILLEGAL_INDEX);
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
