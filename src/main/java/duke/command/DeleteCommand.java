package duke.command;

import duke.command.undoable.Undoable;

import duke.exception.DukeIoException;
import duke.exception.DukeIllegalIndexException;

import duke.module.AutoResponse;
import duke.module.CommandStack;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import duke.task.Task;

/**
 * Represents the "delete" command supported by Duke.
 */
public class DeleteCommand extends Command implements Undoable {

    private static final String DELETE_EMPTY_LIST = "-1";
    private static final String DELETE_ALL = "all";

    private String detail;
    private Task[] deletedTasks;
    private String deletedIndex;

    public DeleteCommand(String detail)  {
        this.detail = detail.trim();
    }

    /**
     * Deletes a {@code Task} from the {@code TaskList}.
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
     * Returns the result of deleting a {@code Task} from the {@code TaskList}.
     *
     * @param taskList List of tasks to manage.
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
            this.deletedTasks = new Task[] { taskList.deleteTaskAt(index) };
            this.deletedIndex = "" + index;
            message = new String[] { AutoResponse.DUKE_DELETE_TASK,
                                     "  " + this.deletedTasks[0].getStatus(),
                                     String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()) };
        } catch (NumberFormatException e) {
            if (this.detail.isEmpty()) {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_MISSING_INDEX);
            }
            if (taskList.isEmpty()) {
                this.deletedTasks = new Task[0];
                this.deletedIndex = DELETE_EMPTY_LIST;
                message = new String[] { AutoResponse.DUKE_NO_TASKS };
            } else if (this.detail.equals(DELETE_ALL)) {
                this.deletedTasks = taskList.toArray();
                taskList.deleteAllTasks();
                this.deletedIndex = DELETE_ALL;
                message = new String[] { AutoResponse.DUKE_DELETE_ALL_TASKS };
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }

        // Add this command to the CommandStack
        commandStack.addUndo(this);
        // Save the updated taskList
        storage.saveTasks(taskList);

        return message;
    }

    /**
     * Undoes the deletion of a command.
     * In other words, adds back the commands that were deleted.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes if necessary.
     * @return The result of undoing this command.
     * @throws DukeIoException If an error occurs while saving.
     */
    @Override
    public String[] undo(TaskList taskList, CommandStack commandStack, Storage storage) throws DukeIoException {
        assert this.deletedTasks != null : "DeleteCommand.java (line 110) : deletedTasks should not be null.";

        // Add the deleted tasks back.
        String[] message;
        switch (this.deletedIndex) {
        case DELETE_EMPTY_LIST:
            message = new String[] { AutoResponse.DUKE_UNDO_DELETE_NO_TASK };
            break;
        case DELETE_ALL:
            for (Task task : this.deletedTasks) {
                taskList.addTask(task);
            }
            message = taskList.listAll();
            message[0] = AutoResponse.DUKE_UNDO_DELETE_TASK;
            break;
        default:
            taskList.addTaskAt(Integer.parseInt(this.deletedIndex), this.deletedTasks[0]);
            message = new String[] { AutoResponse.DUKE_UNDO_DELETE_TASK,
                                     "  " + this.deletedTasks[0].getStatus() };
            break;
        }

        // Add this command to redo stack
        commandStack.addRedo(this);
        // Save the modified taskList
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
     * @throws DukeIoException If an error occurs while saving.
     */
    @Override
    public String[] redo(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIllegalIndexException, DukeIoException {
        assert this.deletedTasks != null : "DeleteCommand.java (line 147) : deletedTasks should not be null.";

        String[] message;
        switch (this.deletedIndex) {
        case DELETE_EMPTY_LIST:
            message = new String[] { AutoResponse.DUKE_REDO_DELETE_NO_TASK };
            break;
        case DELETE_ALL:
            taskList.deleteAllTasks();
            message = new String[] { AutoResponse.DUKE_REDO_DELETE_ALL_TASKS };
            break;
        default:
            int index = Integer.parseInt(this.deletedIndex);
            message = new String[] { AutoResponse.DUKE_REDO_DELETE_TASK,
                                     "  " + taskList.deleteTaskAt(index).getStatus() };
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
     *
     * @return False.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

}