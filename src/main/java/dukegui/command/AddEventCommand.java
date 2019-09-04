package dukegui.command;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;
import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.AutoResponse;
import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;

import duke.task.EventTask;
import duke.task.Task;

/**
 * Represents the "event" command supported by Duke.
 */
public class AddEventCommand extends Command {

    /** "{@value DELIMITER_EVENT_DATE}" : To be used when splitting {@link #description}. */
    private static final String DELIMITER_EVENT_DATE = "/at";

    /** Should contain the description and event date of this <code>EventTask</code>. */
    private String description;

    public AddEventCommand(String description) {
        this.description = description;
    }

    /**
     * Returns the result of adding a {@link EventTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description or date of task is missing.
     * @throws DukeDateFormatException When the date is formatted incorrectly.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, Storage storage)
            throws DukeIllegalArgumentException, DukeDateFormatException, DukeIOException {
        String[] arg = this.description.split(DELIMITER_EVENT_DATE);

        // Check for errors
        this.throwIfInvalid(arg);

        // Have Duke parse the string into date and time
        String date = arg[1].trim();
        DukeDate dukeDate = Parser.parseToDate(date);

        // Add task to the TaskList
        Task task = new EventTask(arg[0].trim(), dukeDate);
        taskList.addTask(task);

        // Save new task to the storage file
        storage.saveTasks(taskList);

        return (new StringBuilder(AutoResponse.DUKE_ADD_TASK))
                .append("\n")
                .append("  ")
                .append(task.getStatus())
                .append("\n")
                .append(String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()))
                .toString();
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

}
