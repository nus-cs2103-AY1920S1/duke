package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgument;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class PriorityCommand implements Command {
    private int index;
    private String priority;

    public PriorityCommand(int index, String priority) {
        this.index = index;
        this.priority = priority;
    }

    /**
     * Execute the command and update the task priority. Takes in a tasklist to which
     * the task will be updated, a ui which will output the message and a
     * storage which updates the task in the file
     *
     * @param tasks   a tasklist containing all the existing tasks
     * @param ui      a ui which will output the message
     * @param storage a storage object which will write to the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.getTask(index);
        switch (priority.toLowerCase()) {
        case "high":
            t.setHighPriority();
            break;
        case "medium":
            t.setMedPriority();
            break;
        case "low":
            t.setLowPriority();
            break;
        default:
            throw new InvalidArgument();
        }

        storage.write(tasks);
        return ui.updatePriorityMessage(t);
    }
}
