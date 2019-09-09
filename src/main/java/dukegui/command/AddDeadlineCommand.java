package dukegui.command;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;
import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.AutoResponse;
import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;

import duke.task.DeadlineTask;
import duke.task.Task;

/**
 * Represents the "deadline" command supported by Duke.
 */
public class AddDeadlineCommand extends Command {

    /** "{@value DELIMITER_DEADLINE_DATE}" : To be used when splitting {@link #description}. */
    private static final String DELIMITER_DEADLINE_DATE = "/by";

    /** Should contain the description and due date of a <code>DeadlineTask</code>. */
    private String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Returns the result of adding a {@link DeadlineTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes.
     * @return Result of executing this {@code AddDeadlineCommand}.
     * @throws DukeIllegalArgumentException When the description or date of task is missing.
     * @throws DukeDateFormatException When the date is formatted incorrectly.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, Storage storage)
            throws DukeIllegalArgumentException, DukeDateFormatException, DukeIOException {
        String[] arg = this.description.split(DELIMITER_DEADLINE_DATE);

        // Check for errors
        this.throwIfInvalid(arg);

        // Have Duke parse the string into date and time
        String date = arg[1].trim();
        DukeDate dukeDate = Parser.parseToDate(date);

        // Add task to the TaskList
        Task task = new DeadlineTask(arg[0].trim(), dukeDate);
        taskList.addTask(task);

        // Save new task to the storage file
        storage.saveTasks(taskList);

        return String.join("\n",
                           AutoResponse.DUKE_ADD_TASK,
                           "  " + task.getStatus(),
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
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_DEADLINE_DATE);
        }
    }

    /**
     * Returns false.
     *
     * @return False.
     * @see Command#isExit()
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
