package duke.command;

import duke.exception.DukeException;
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
    private Task todoTask;

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
        if (this.description.isEmpty()) {
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_TASK_DESCRIPTION);
        }

        this.todoTask = new TodoTask(this.description);
        taskList.addTask(this.todoTask);

        // Display the result to the user
        ui.printToUser(AutoResponse.DUKE_ADD_TASK,
                       "  " + this.todoTask.getStatus(),
                       String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()));

        // Add this command to the undoStack
        undoStack.addUndoable(this);

        // Save new tasks to the save file
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
        // TODO : javadocs and update line number
        assert this.todoTask != null : "AddTodoCommand.java (line 66) : todoTask should not be null";
        assert taskList.delete(this.todoTask) : "AddTodoCommand.java (line 67) : Undo error";

        // Save the modified taskList
        storage.saveTasks(taskList);

        // Display the result to the user
        ui.printToUser(AutoResponse.DUKE_UNDO_ADD_TASK, "  " + this.todoTask.getStatus());
    }
}
