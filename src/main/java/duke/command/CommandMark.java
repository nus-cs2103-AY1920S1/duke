package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.History;
import duke.util.Storage;
import duke.util.Ui;

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
     * Marks a specific Task in the TaskList as done, saves the change to disk, and returns a confirmation.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @param history History of commands for the current session.
     * @return Duke's response to the Command as a String.
     * @throws DukeException If marking as done or file saving to disk fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) throws DukeException {
        Task taskToMark;

        try {
            taskToMark = tasks.get(taskNumber);
            taskToMark.markDone();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number.");
        }

        storage.save(tasks);
        history.add(String.format("done %d", taskNumber), tasks);

        return ui.getTaskMarkedDoneMessage(taskToMark);
    }
}
