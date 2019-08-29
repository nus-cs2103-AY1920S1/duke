package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;

/**
 * A Command to mark as task as done.
 */
public class CommandMark extends Command {
    private int taskNumber;

    /**
     * Instantiates a new 'Mark' Command with a given task number.
     * @param taskNumber The position (1-indexed) of the Task to mark as done in the TaskList.
     */
    public CommandMark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a specific Task in the TaskList as done, saves the change to disk, and prints a confirmation.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @throws DukeException If marking as done or file saving to disk fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToMark;

        try {
            taskToMark = tasks.get(taskNumber);
            taskToMark.markDone();
        } catch (Exception e) {
            throw new DukeException("Invalid task number.");
        }

        storage.save(tasks);

        ui.printTaskMarkedDone(taskToMark);
    }
}
