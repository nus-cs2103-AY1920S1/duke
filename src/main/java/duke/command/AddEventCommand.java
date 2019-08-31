package duke.command;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;
import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import duke.task.EventTask;
import duke.task.Task;

public class AddEventCommand extends Command {

    private static final String DUKE_ADD_TASK = "Got it. I've added this task:";
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";

    private static final String ERROR_MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task "
            + "cannot be empty.";
    private static final String ERROR_MISSING_DESCRIPTION_AND_DATE = "☹ OOPS!!! Description and dates of a task "
            + "cannot be empty.";
    private static final String ERROR_MISSING_EVENT_DATE = "☹ OOPS!!! Deadline dates must be specified after \"/at.\"";

    private static final String DELIMITER_EVENT_DATE = "/at";

    private String description;

    public AddEventCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeIllegalArgumentException, DukeDateFormatException, DukeIOException {
        String[] arg = this.description.split(DELIMITER_EVENT_DATE);

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
            throw new DukeIllegalArgumentException(ERROR_MISSING_EVENT_DATE);
        }

        // Have Duke parse the string into date and time
        DukeDate dukeDate = Parser.parseToDate(date);

        // Add task to the TaskList
        Task task = new EventTask(arg[0], dukeDate);
        taskList.addTask(task);

        // Save new task to the storage file
        storage.saveTasks(taskList);

        // Display the result to the user
        ui.printToUser(DUKE_ADD_TASK,
                       "  " + task.getStatus(),
                       String.format(DUKE_NUMBER_OF_TASKS, taskList.getSize()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
