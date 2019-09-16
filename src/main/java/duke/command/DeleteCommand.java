package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {

    private final int index;
    private int[] indexes;

    /**
     * Initialises a DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the Delete Command and removes the task from the Linked List and file.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task t = tasks.getList().get(index);
            storage.deleteTaskFromFile(t);
            tasks.deleteTask(t);
            return ("Noted. I've removed this task:\n" + t + tasks.getStatus());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number. Not within range");
        }
    }

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public boolean isExit() {
        return false;
    }
}
