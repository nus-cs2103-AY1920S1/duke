package duke.command;

import duke.command.undoable.Undoable;

import duke.exception.DukeIoException;
import duke.exception.DukeIllegalIndexException;

import duke.module.AutoResponse;
import duke.module.CommandStack;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Represents the "done" command supported by Duke.
 */
public class DoneCommand extends Command implements Undoable {

    private static final String DONE_EMPTY_LIST = "-1";
    private static final String DONE_ALL = "all";

    private String detail;
    private String finishedIndex;

    public DoneCommand(String detail) {
        this.detail = detail.trim();
    }

    /**
     * Marks a <code>Task</code> in the <code>TaskList</code> as done.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalIndexException When the index inputted is out of bounds or missing.
     * @throws DukeIoException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, CommandStack commandStack, Ui ui, Storage storage)
            throws DukeIllegalIndexException, DukeIoException {
        // Display the result to the user
        ui.printToUser(this.getMessage(taskList, commandStack, storage));
    }

    /**
     * Returns the response of marking a <code>Task</code> in the <code>TaskList</code> as done.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalIndexException When the index inputted is out of bounds.
     * @throws DukeIoException When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIllegalIndexException, DukeIoException {
        return String.join("\n", this.getMessage(taskList, commandStack, storage));
    }

    private String[] getMessage(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIllegalIndexException, DukeIoException {
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
                this.finishedIndex = DONE_EMPTY_LIST;
                message = new String[] { AutoResponse.DUKE_NO_TASKS };
            } else if (this.detail.equals(DONE_ALL)) {
                taskList.markAsDoneAllTasks();
                this.finishedIndex = DONE_ALL;
                message = taskList.listAll();
                message[0] = AutoResponse.DUKE_DONE_ALL_TASKS;
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }

        // Add this command to the commandStack
        commandStack.addUndo(this);
        // Save the updated taskList
        storage.saveTasks(taskList);

        return message;
    }

    /**
     * Undoes the "done" command.
     * In other words, changes the status of tasks back to unfinished.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes if necessary.
     * @return The result of undoing this command.
     * @throws DukeIoException If an error occurs while saving.
     * @throws DukeIllegalIndexException If index stored is out of bounds.
     */
    @Override
    public String[] undo(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIoException, DukeIllegalIndexException {
        assert this.finishedIndex != null : "DoneCommand.java (line 107) : finishedIndex should not be null";

        String[] message;
        // Display the result to the user
        switch (this.finishedIndex) {
        case DONE_EMPTY_LIST:
            message = new String[] { AutoResponse.DUKE_UNDO_DONE_NO_TASK };
            break;
        case DONE_ALL:
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
        // Add this command to the redo stack
        commandStack.addRedo(this);
        // Save updated taskList
        storage.saveTasks(taskList);
        return message;
    }

    /**
     * Redoes the undoing of this command.
     *
     * @param taskList     List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage      Storage to save any changes if necessary.
     * @return The result of redoing this command.
     * @throws DukeIllegalIndexException If an error occurs while retrieving a task from the taskList.
     * @throws DukeIoException If an error occurs while saving.
     */
    @Override
    public String[] redo(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIllegalIndexException, DukeIoException {
        assert this.finishedIndex != null : "DoneCommand.java (line 144) : finishedIndex should not be null";

        String[] message;
        // Display the result to the user
        switch (this.finishedIndex) {
        case DONE_EMPTY_LIST:
            message = new String[] { AutoResponse.DUKE_REDO_DONE_NO_TASK };
            break;
        case DONE_ALL:
            taskList.markAsDoneAllTasks();
            message = new String[] { AutoResponse.DUKE_REDO_DONE_ALL_TASKS };
            break;
        default:
            int index = Integer.parseInt(this.finishedIndex);
            taskList.markAsDoneTaskAt(index);
            message = new String[] { AutoResponse.DUKE_REDO_DONE_TASK,
                                     "  " + taskList.getTaskAt(index).getStatus() };
            break;
        }

        // Add this command to the undo stack
        commandStack.addUndo(this);
        // Save updated taskList
        storage.saveTasks(taskList);

        return message;
    }

    /**
     * Returns false.
     * @return False.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

}
