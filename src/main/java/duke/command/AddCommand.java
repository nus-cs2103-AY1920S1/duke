package duke.command;

import duke.DukeException;
import duke.common.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Adds a task to storage file.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a command to add a given task to the storage file.
     *
     * @param task task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes addition of a task.
     *
     * @param taskList list of tasks.
     * @param storage local storage of data.
     * @return message showing the successful addition of a task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        assert task != null;
        taskList.addTask(task);
        storage.save(task.getSimplifiedRepresentation());
        return Message.MESSAGE_ADDED + "\n " + task + "\n"
                + String.format(Message.MESSAGE_SHOW_TASK_SIZE, taskList.size());
    }
}
