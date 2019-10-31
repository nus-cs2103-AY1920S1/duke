package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.History;
import duke.util.Storage;
import duke.util.Ui;

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
     * Deletes a Task from the TaskList, saves the change to disk, and returns a confirmation.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @param history History of commands for the current session.
     * @return Duke's response to the Command as a String.
     * @throws DukeException If task deletion or file saving on disk fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) throws DukeException {
        Task taskToDelete;
        try {
            taskToDelete = tasks.remove(taskNumber);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number.");
        }

        storage.save(tasks);
        history.add(String.format("delete %d", taskNumber), tasks);

        return ui.getTaskDeletedMessage(tasks, taskToDelete);
    }
}
