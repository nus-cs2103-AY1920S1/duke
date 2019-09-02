package command;

import duke.Storage;
import duke.TaskList;
import exception.DukeException;
import exception.DukeInvalidTaskIndexException;
import task.Task;

/**
 * Represents a Command which deletes a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand with a given index to delete from the TaskList.
     * @param index Task index to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Deletes a task from the TaskList.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("You have no tasks to delete.");
        } else if (index < 0 || index >= tasks.getSize()) {
            throw new DukeInvalidTaskIndexException("delete", tasks.getSize());
        } else {
            Task t = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.save(tasks);
            return ("Noted. I've removed this task:\n" + t + "\n"
                    + "Now you have " + tasks.getSize() + " task"
                    + ((tasks.getSize() - 1) == 1 ? " " : "s ") + "in the list.");
        }
    }

    public boolean isExit() {
        return false;
    }
}