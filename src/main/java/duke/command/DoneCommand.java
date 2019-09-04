package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Represents the "done" command supported by Duke.
 */
public class DoneCommand extends Command {

    /** {@value DUKE_MARK_AS_DONE} */
    private static final String DUKE_MARK_AS_DONE = "Nice! I've marked this task as done:";
    /** {@value DUKE_DONE_ALL_TASKS} */
    private static final String DUKE_DONE_ALL_TASKS = "Nice! All of your tasks are marked as done.";

    /** {@value ERROR_ILLEGAL_INDEX} */
    private static final String ERROR_ILLEGAL_INDEX = "☹ OOPS!!! The index must be a number "
            + "separated by one whitespace.";

    /** Should contain the index of the Task to mark as done or the word "all." */
    private String detail;

    public DoneCommand(String detail) {
        this.detail = detail;
    }

    /**
     * Marks a <code>Task</code> in the <code>TaskList</code> as done.
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
            taskList.markAsDoneTaskAt(index);
            ui.printToUser(DUKE_MARK_AS_DONE, "  " + taskList.getTaskAt(index).getStatus());
        } catch (NumberFormatException e) {
            if (this.detail.equals("all")) {
                taskList.markAsDoneAllTasks();
                String[] lines = taskList.listAll();
                lines[0] = DUKE_DONE_ALL_TASKS;
                ui.printToUser(lines);
            } else {
                throw new DukeIllegalIndexException(ERROR_ILLEGAL_INDEX);
            }
        }
        storage.saveTasks(taskList);
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
            response = new StringBuilder(DUKE_MARK_AS_DONE)
                    .append("\n")
                    .append("  ")
                    .append(taskList.getTaskAt(index).getStatus())
                    .toString();
        } catch (NumberFormatException e) {
            if (taskList.getSize() == 0) {
                // TODO Change package
                response = "You currently have no tasks in your list.";
            } else if (this.detail.equals("all")) {
                taskList.markAsDoneAllTasks();
                String[] lines = taskList.listAll();
                StringBuilder sb = new StringBuilder(DUKE_MARK_AS_DONE);
                for (int i = 1; i < lines.length; i++) {
                    sb.append(lines[i]);
                    sb.append("\n");
                }
                response = sb.toString();
            } else {
                throw new DukeIllegalIndexException(ERROR_ILLEGAL_INDEX);
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
