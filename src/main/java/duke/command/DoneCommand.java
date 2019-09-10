package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DoneCommand extends Command {
    private final int index;

    /**
     * Initialises a DoneCommand.
     *
     * @param index The index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Done Command and marks the task as done.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {

        try {
            Task t = tasks.getList().get(index);

            // Delete from file, mark as done, reinsert into file
            storage.deleteTaskFromFile(t);
            t.markAsDone();
            storage.appendTaskToFile(t);
            assert (t.getStatus()) : "Not marked as done";

            return ("Nice! I've marked this task as done:\n" + t);
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
