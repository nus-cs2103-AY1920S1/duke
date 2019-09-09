package duke.command;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;
import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.AutoResponse;
import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.UndoStack;

import duke.task.EventTask;
import duke.task.Task;

/**
 * Represents the "event" command supported by Duke.
 */
public class AddEventCommand extends Command implements Undoable {

    private static final String DELIMITER_EVENT_DATE = "/at";

    private String description;
    private Task eventTask;

    public AddEventCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a {@link EventTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description or date of task is missing.
     * @throws DukeDateFormatException When the date is formatted incorrectly.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, UndoStack undoStack, Ui ui, Storage storage)
            throws DukeIllegalArgumentException, DukeDateFormatException, DukeIOException {
        String[] arg = this.description.split(DELIMITER_EVENT_DATE);

        // Check for errors
        this.throwIfInvalid(arg);

        // Have Duke parse the string into date and time
        String date = arg[1].trim();
        DukeDate dukeDate = Parser.parseToDate(date);

        // Add task to the TaskList
        eventTask = new EventTask(arg[0].trim(), dukeDate);
        taskList.addTask(eventTask);

        // Add this command to the undoStack
        undoStack.addUndoable(this);

        // Save new task to the storage file
        storage.saveTasks(taskList);

        // Display the result to the user
        ui.printToUser(AutoResponse.DUKE_ADD_TASK,
                       "  " + eventTask.getStatus(),
                       String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()));
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
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_EVENT_DATE);
        }
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
        // TODO : javadocs and update line numbers
        assert this.eventTask != null : "AddEventCommand.java (line 115) : deadlineTask should not be empty";
        assert taskList.delete(this.eventTask) : "AddEventCommand.java (line 116) : Undo error.";

        // Save the modified taskList
        storage.saveTasks(taskList);

        // Display the result to the user
        ui.printToUser(AutoResponse.DUKE_UNDO_ADD_TASK, "  " + this.eventTask.getStatus());
    }

}
