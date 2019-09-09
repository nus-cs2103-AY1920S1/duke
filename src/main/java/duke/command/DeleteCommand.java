package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.UndoStack;

import duke.task.Task;
// TODO : java docs
/**
 * Represents the "delete" command supported by Duke.
 */
public class DeleteCommand extends Command implements Undoable {

    private String detail;
    private Task[] deletedTask;

    public DeleteCommand(String detail)  {
        this.detail = detail.trim();
    }

    /**
     * Deletes a <code>Task</code> from the <code>TaskList</code>.
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
        try {
            int index = Integer.parseInt(this.detail);
            this.deletedTask = new Task[] { taskList.deleteTaskAt(index) };
            // Display the result to the user.
            ui.printToUser(AutoResponse.DUKE_DELETE_TASK,
                           "  " + this.deletedTask[0].getStatus(),
                           String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()));
        } catch (NumberFormatException e) {
            if (this.detail.isEmpty()) {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_MISSING_INDEX);
            }
            if (taskList.isEmpty()) {
                this.deletedTask = new Task[0];
                // Display the result to the user
                ui.printToUser(AutoResponse.DUKE_NO_TASKS);
            } else if (this.detail.equals("all")) {
                this.deletedTask = taskList.toArray();
                taskList.deleteAllTasks();
                // Display the result to the user
                ui.printToUser(AutoResponse.DUKE_DELETE_ALL_TASKS);
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }
        // Add this command to the UndoStack
        undoStack.addUndoable(this);
        // Save the updated taskList
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

    @Override
    public void undo(TaskList taskList, Ui ui, Storage storage) throws DukeIOException {
        assert this.deletedTask != null : "DeleteCommand.java (line 78) : deleteTask should not be null.";
        // Add the deleted tasks back.
        for (Task task : this.deletedTask) {
            taskList.addTask(task);
        }

        // Save the modified taskList
        storage.saveTasks(taskList);

        // Display the result to the user
        if (this.deletedTask.length == 0) {
            ui.printToUser(AutoResponse.DUKE_UNDO_DELETE_NO_TASK);
        } else {
            String[] lines = taskList.listAll();
            lines[0] = AutoResponse.DUKE_UNDO_DELETE_TASK;
            ui.printToUser(lines);
        }
    }

}
