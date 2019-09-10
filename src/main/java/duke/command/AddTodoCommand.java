package duke.command;

import duke.command.undoable.Undoable;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.UndoStack;

import duke.task.Task;
import duke.task.TodoTask;

/**
 * Represents the "todo" command supported by Duke.
 */
public class AddTodoCommand extends Command implements Undoable {

    private String description;

    public AddTodoCommand(String description) {
        this.description = description.trim();
    }

    /**
     * Adds a {@link TodoTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description of task is missing.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, UndoStack undoStack, Ui ui, Storage storage)
            throws DukeIllegalArgumentException, DukeIOException {
        // Display the result to the user
        ui.printToUser(this._execute(taskList, undoStack, storage));
    }

    /**
     * Returns the result of adding a {@link TodoTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description of task is missing.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, UndoStack undoStack, Storage storage)
            throws DukeIllegalArgumentException, DukeIOException {
        return String.join("\n", this._execute(taskList, undoStack, storage));
    }

    private String[] _execute(TaskList taskList, UndoStack undoStack, Storage storage)
            throws DukeIllegalArgumentException, DukeIOException {
        if (this.description.isEmpty()) {
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_TASK_DESCRIPTION);
        }

        Task todoTask = new TodoTask(this.description);
        taskList.addTask(todoTask);

        // Add this command to the undoStack
        undoStack.addUndoable(this);

        // Save new tasks to the save file
        storage.saveTasks(taskList);

        // Display the result to the user
        return new String[] { AutoResponse.DUKE_ADD_TASK,
                              "  " + todoTask.getStatus(),
                              String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()) };
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

    /**
     * Undoes the addition of a todo task.
     * In other words, deletes the last todo task added.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes if necessary.
     * @return The result of undoing this command.
     * @throws DukeIOException If an error occurs while saving.
     */
    @Override
    public String[] undo(TaskList taskList, Storage storage) throws DukeIOException {
        Task todoTask = taskList.deleteLastTask();

        // Save the modified taskList
        storage.saveTasks(taskList);

        // Display the result to the user
        return new String[] { AutoResponse.DUKE_UNDO_ADD_TASK,
                              "  " + todoTask.getStatus(),
                              String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()) };
    }

}
