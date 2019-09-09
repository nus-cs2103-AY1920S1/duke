package dukegui.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;

import duke.task.Task;
import duke.task.TodoTask;

/**
 * Represents the "todo" command supported by Duke.
 */
public class AddTodoCommand extends Command {

    /** Should contain the description of a <code>TodoTask</code>. */
    private String description;

    public AddTodoCommand(String description) {
        this.description = description.trim();
    }

    /**
     * Returns the result of adding a {@link TodoTask} to the <code>TaskList</code>.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalArgumentException When the description of task is missing.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public String getResponse(TaskList taskList, Storage storage)
            throws DukeIllegalArgumentException, DukeIOException {
        if (this.description.isEmpty()) {
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_TASK_DESCRIPTION);
        }

        Task task = new TodoTask(this.description);
        taskList.addTask(task);
        storage.saveTasks(taskList);

        return String.join("\n",
                           AutoResponse.DUKE_ADD_TASK,
                           "  " + task.getStatus(),
                           String.format(AutoResponse.DUKE_NUMBER_OF_TASKS, taskList.getSize()));
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
