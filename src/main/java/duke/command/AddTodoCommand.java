package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import duke.task.Task;
import duke.task.TodoTask;

/**
 * Represents the "todo" command supported by Duke.
 */
public class AddTodoCommand extends Command {

    /** {@value DUKE_ADD_TASK} */
    private static final String DUKE_ADD_TASK = "Got it. I've added this task:";
    /** {@value DUKE_NUMBER_OF_TASKS} */
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";

    /** {@value ERROR_MISSING_TASK_DESCRIPTION} */
    private static final String ERROR_MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task "
            + "cannot be empty.";

    /** Should contain the description of a <code>TodoTask</code>. */
    private String description;

    public AddTodoCommand(String description) {
        this.description = description.trim();
    }

    /**
     * Adds a {@link TodoTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description of task is missing.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeIllegalArgumentException, DukeIOException {
        if (this.description.isEmpty()) {
            throw new DukeIllegalArgumentException(ERROR_MISSING_TASK_DESCRIPTION);
        }

        Task task = new TodoTask(this.description);
        taskList.addTask(task);
        ui.printToUser(DUKE_ADD_TASK,
                       "  " + task.getStatus(),
                       String.format(DUKE_NUMBER_OF_TASKS, taskList.getSize()));
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

}
