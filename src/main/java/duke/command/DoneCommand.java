package duke.command;

import duke.command.undoable.Undoable;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.UndoStack;

/**
 * Represents the "done" command supported by Duke.
 */
public class DoneCommand extends Command implements Undoable {

    private String detail;
    private String finishedIndex;

    public DoneCommand(String detail) {
        this.detail = detail.trim();
    }

    /**
     * Marks a <code>Task</code> in the <code>TaskList</code> as done.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalIndexException When the index inputted is out of bounds or missing.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, UndoStack undoStack, Ui ui, Storage storage)
            throws DukeIllegalIndexException, DukeIOException {
        // Display the result to the user
        ui.printToUser(this._execute(taskList, undoStack, storage));
    }

    /**
     * Returns the response of marking a <code>Task</code> in the <code>TaskList</code> as done.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalIndexException When the index inputted is out of bounds.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, UndoStack undoStack, Storage storage)
            throws DukeIllegalIndexException, DukeIOException{
        return String.join("\n", this._execute(taskList, undoStack, storage));
    }

    private String[] _execute(TaskList taskList, UndoStack undoStack, Storage storage)
            throws DukeIllegalIndexException, DukeIOException {
        String[] message;

        try {
            int index = Integer.parseInt(this.detail);
            taskList.markAsDoneTaskAt(index);
            this.finishedIndex = "" + index;
            message = new String[] { AutoResponse.DUKE_MARK_AS_DONE, "  " + taskList.getTaskAt(index).getStatus() };
        } catch (NumberFormatException e) {
            if (this.detail.isEmpty()) {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_MISSING_INDEX);
            }

            if (taskList.isEmpty()) {
                this.finishedIndex = "-1";
                message = new String[] { AutoResponse.DUKE_NO_TASKS };
            } else if (this.detail.equals("all")) {
                taskList.markAsDoneAllTasks();
                this.finishedIndex = "all";
                message = taskList.listAll();
                message[0] = AutoResponse.DUKE_DONE_ALL_TASKS;
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }

        // Add this command to the undoStack
        undoStack.addUndoable(this);
        // Save the updated taskList
        storage.saveTasks(taskList);

        return message;
    }

    /**
     * Returns false.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Undoes the "done" command.
     * In other words, changes the status of tasks back to unfinished.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes if necessary.
     * @return The result of undoing this command.
     * @throws DukeIOException If an error occurs while saving.
     * @throws DukeIllegalIndexException If index stored is out of bounds.
     */
    @Override
    public String[] undo(TaskList taskList, Storage storage)
            throws DukeIOException, DukeIllegalIndexException {
        assert this.finishedIndex != null : "DoneCommand.java (line 83) : finishedIndex should not be null";

        String[] message;
        // Display the result to the user
        switch (this.finishedIndex) {
        case "-1":
            message = new String[] { AutoResponse.DUKE_UNDO_DONE_NO_TASK };
            break;
        case "all":
            taskList.markAsUnfinishedAllTasks();
            message = new String[] { AutoResponse.DUKE_UNDO_DONE_ALL_TASKS };
            break;
        default:
            int index = Integer.parseInt(this.finishedIndex);
            taskList.markAsUnfinishedTaskAt(index);
            message = new String[] { AutoResponse.DUKE_UNDO_DONE_TASK,
                                     "  " + taskList.getTaskAt(index).getStatus() };
            break;
        }
        // Save updated taskList
        storage.saveTasks(taskList);
        return message;
    }
}
