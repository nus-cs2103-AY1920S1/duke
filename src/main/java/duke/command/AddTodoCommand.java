package duke.command;

import duke.command.undoable.Undoable;

import duke.exception.DukeIoException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.AutoResponse;
import duke.module.CommandStack;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import duke.task.Task;
import duke.task.TodoTask;

/**
 * Represents the "todo" command supported by Duke.
 */
public class AddTodoCommand extends Command implements Undoable {

    private String description;
    private Task todoTask;

    public AddTodoCommand(String description) {
        this.description = description.trim();
    }

    /**
     * Adds a {@link TodoTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description of task is missing.
     * @throws DukeIoException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, CommandStack commandStack, Ui ui, Storage storage)
            throws DukeIllegalArgumentException, DukeIoException {
        // Display the result to the user
        ui.printToUser(this.getMessage(taskList, commandStack, storage));
    }

    /**
     * Returns the result of adding a {@link TodoTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description of task is missing.
     * @throws DukeIoException When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIllegalArgumentException, DukeIoException {
        return String.join("\n", this.getMessage(taskList, commandStack, storage));
    }

    private String[] getMessage(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIllegalArgumentException, DukeIoException {
        if (this.description.isEmpty()) {
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_TASK_DESCRIPTION);
        }

        Task todoTask = new TodoTask(this.description);
        taskList.addTask(todoTask);

        // Add this command to the commandStack
        commandStack.addUndo(this);

        // Save new tasks to the save file
        storage.saveTasks(taskList);

        return new String[] { AutoResponse.DUKE_ADD_TASK,
                              "  " + todoTask.getStatus(),
                              String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()) };
    }

    /**
     * Undoes the addition of a todo task.
     * In other words, deletes the last todo task added.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes if necessary.
     * @return The result of undoing this command.
     * @throws DukeIoException If an error occurs while saving.
     */
    @Override
    public String[] undo(TaskList taskList, CommandStack commandStack, Storage storage) throws DukeIoException {
        this.todoTask = taskList.deleteLastTask();

        // Add this command to the redo stack
        commandStack.addRedo(this);
        // Save the modified taskList
        storage.saveTasks(taskList);

        // Display the result to the user
        return new String[] { AutoResponse.DUKE_UNDO_ADD_TASK,
                              "  " + todoTask.getStatus(),
                              String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()) };
    }

    /**
     * Redoes the undoing of this command.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes if necessary.
     * @return The result of redoing this command.
     * @throws DukeIoException If an error occurs while saving.
     */
    @Override
    public String[] redo(TaskList taskList, CommandStack commandStack, Storage storage) throws DukeIoException {
        assert this.todoTask != null : "AddTodoCommand.java (line 117) : todoTask should not be null.";
        // Add the deleted task back to the taskList
        taskList.addTask(this.todoTask);
        // Add this command to the undo stack
        commandStack.addUndo(this);
        // Save the modified taskList
        storage.saveTasks(taskList);

        return new String[] { AutoResponse.DUKE_REDO_ADD_TASK,
                              "  " + this.todoTask.getStatus(),
                              String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()) };
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
