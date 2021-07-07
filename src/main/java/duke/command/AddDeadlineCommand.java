package duke.command;

import duke.command.undoable.Undoable;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;
import duke.exception.DukeIoException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.AutoResponse;
import duke.module.CommandStack;
import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import duke.task.DeadlineTask;
import duke.task.Task;

/**
 * Represents the "deadline" command supported by Duke.
 */
public class AddDeadlineCommand extends Command implements Undoable {

    private static final String DELIMITER_DEADLINE_DATE = "/by";

    private String description;
    private Task deadlineTask;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a {@link DeadlineTask} to the <code>TaskList</code>.
     *
     * @param taskList  List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param ui        UI to show result to user.
     * @param storage   Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description or date of task is missing.
     * @throws DukeDateFormatException      When the date is formatted incorrectly.
     * @throws DukeIoException              When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, CommandStack commandStack, Ui ui, Storage storage)
            throws DukeIllegalArgumentException, DukeDateFormatException, DukeIoException {
        // Display the result to the user
        ui.printToUser(this.getMessage(taskList, commandStack, storage));
    }

    /**
     * Returns the result of adding a {@link DeadlineTask} to the <code>TaskList</code>.
     *
     * @param taskList  List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage   Storage to save any changes.
     * @return Result of executing this {@code AddDeadlineCommand}.
     * @throws DukeIllegalArgumentException When the description or date of task is missing.
     * @throws DukeDateFormatException      When the date is formatted incorrectly.
     * @throws DukeIoException              When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIllegalArgumentException, DukeDateFormatException, DukeIoException {
        return String.join("\n", this.getMessage(taskList, commandStack, storage));
    }

    private String[] getMessage(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIllegalArgumentException, DukeDateFormatException, DukeIoException {
        String[] arg = this.description.split(DELIMITER_DEADLINE_DATE);

        // Check for errors
        this.throwIfInvalid(arg);

        // Have Duke parse the string into date and time
        String date = arg[1].trim();
        DukeDate dukeDate = Parser.parseToDate(date);

        // Add task to the TaskList
        Task deadlineTask = new DeadlineTask(arg[0].trim(), dukeDate);
        taskList.addTask(deadlineTask);

        // Add this command to the UndoStack
        commandStack.addUndo(this);

        // Save new task to the storage file
        storage.saveTasks(taskList);

        return new String[]{ AutoResponse.DUKE_ADD_TASK,
                             "  " + deadlineTask.getStatus(),
                             String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()) };
    }

    private void throwIfInvalid(String[] arg)
            throws DukeIllegalArgumentException {
        String description = "";
        boolean hasDescription;
        try {
            description = arg[0].trim();
            hasDescription = !description.isEmpty();
        } catch (ArrayIndexOutOfBoundsException e) {
            hasDescription = false;
        }

        String date = "";
        boolean hasDate;
        try {
            date = arg[1].trim();
            hasDate = !date.isEmpty();
        } catch (ArrayIndexOutOfBoundsException e) {
            hasDate = false;
        }

        // Check for errors
        if (!hasDescription && !hasDate) {
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_DESCRIPTION_AND_DATE);
        } else if (!hasDescription) {
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_TASK_DESCRIPTION);
        } else if (!hasDate) {
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_DEADLINE_DATE);
        }
    }

    /**
     * Undoes the addition of a deadline task.
     * In other words, deletes the last deadline task added.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage  Storage to save any changes if necessary.
     * @return The result of undoing this command.
     * @throws DukeIoException If an error occurs while saving.
     */
    @Override
    public String[] undo(TaskList taskList, CommandStack commandStack, Storage storage) throws DukeIoException {
        this.deadlineTask = taskList.deleteLastTask();

        // Add this command to the redo stack
        commandStack.addRedo(this);
        // Save the modified taskList
        storage.saveTasks(taskList);

        // Display the result to the user.
        return new String[] { AutoResponse.DUKE_UNDO_ADD_TASK,
                              "  " + deadlineTask.getStatus(),
                              String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize())};
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
        assert this.deadlineTask != null : "AddDeadlineCommand.java (line 162) : deadlineTask should not be null";

        // Add the task that was removed back
        taskList.addTask(this.deadlineTask);
        // Add this command to the undo stack
        commandStack.addUndo(this);
        // Save the modified taskList
        storage.saveTasks(taskList);

        return new String[]{ AutoResponse.DUKE_REDO_ADD_TASK,
                             "  " + this.deadlineTask.getStatus(),
                             String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()) };
    }

    /**
     * Returns false.
     *
     * @return False.
     * @see Command#shouldExit()
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

}
