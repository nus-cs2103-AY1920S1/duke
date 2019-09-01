package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;

/**
 * A Command to delete a task from the task list.
 */
public class CommandDelete extends Command {
    private int taskNumber;

    /**
     * Instantiates a new 'Delete' Command with a given task number.
     * @param taskNumber The position (1-indexed) of the Task to delete from the TaskList.
     */
    public CommandDelete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a Task from the TaskList, saves the change to disk, and prints a confirmation.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @return Duke's response to the Command as a String.
     * @throws DukeException If task deletion or file saving on disk fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToDelete;
        try {
            taskToDelete = tasks.remove(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Invalid task number.");
        }

        storage.save(tasks);

        return ui.getTaskDeletedMessage(tasks, taskToDelete);
    }
}
