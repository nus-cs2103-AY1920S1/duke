package duke.command;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;
import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import duke.task.DeadlineTask;
import duke.task.Task;

/**
 * Represents the "deadline" command supported by Duke.
 */
public class AddDeadlineCommand extends Command {

    /** {@value DUKE_ADD_TASK} */
    private static final String DUKE_ADD_TASK = "Got it. I've added this task:";
    /** {@value DUKE_NUMBER_OF_TASKS} */
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";

    /** {@value ERROR_MISSING_TASK_DESCRIPTION} */
    private static final String ERROR_MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task "
            + "cannot be empty.";
    /** {@value ERROR_MISSING_DESCRIPTION_AND_DATE} */
    private static final String ERROR_MISSING_DESCRIPTION_AND_DATE = "☹ OOPS!!! Description and dates of a task "
            + "cannot be empty.";
    /** {@value ERROR_MISSING_DEADLINE_DATE} */
    private static final String ERROR_MISSING_DEADLINE_DATE = "☹ OOPS!!! Deadline dates must be "
            + "specified after \"/by.\"";

    /** "{@value DELIMITER_DEADLINE_DATE}" : To be used when splitting {@link #description}. */
    private static final String DELIMITER_DEADLINE_DATE = "/by";

    /** Should contain the description and due date of a <code>DeadlineTask</code>. */
    private String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a {@link DeadlineTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description or date of task is missing.
     * @throws DukeDateFormatException When the date is formatted incorrectly.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeIllegalArgumentException, DukeDateFormatException, DukeIOException {
        String[] arg = this.description.split(DELIMITER_DEADLINE_DATE);

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
            throw new DukeIllegalArgumentException(ERROR_MISSING_DESCRIPTION_AND_DATE);
        } else if (!hasDescription) {
            throw new DukeIllegalArgumentException(ERROR_MISSING_TASK_DESCRIPTION);
        } else if (!hasDate) {
            throw new DukeIllegalArgumentException(ERROR_MISSING_DEADLINE_DATE);
        }

        // Have Duke parse the string into date and time
        DukeDate dukeDate = Parser.parseToDate(date);

        // Add task to the TaskList
        Task task = new DeadlineTask(arg[0], dukeDate);
        taskList.addTask(task);

        // Save new task to the storage file
        storage.saveTasks(taskList);

        // Display the result to the user
        ui.printToUser(DUKE_ADD_TASK,
                "  " + task.getStatus(),
                String.format(DUKE_NUMBER_OF_TASKS, taskList.getSize()));
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
