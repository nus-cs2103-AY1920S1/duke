package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an instruction to delete a specified task in Duke.
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx, boolean isExit) {
        super(isExit);
        this.idx = idx;
    }

    /**
     * Deletes a specified task from the TaskList, informs the user and updates the hard disk.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param ui the Ui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     * @return the response containing the response and boolean flag to exit the program.
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) {
        // delete the task at corresponding index
        Task task = taskList.delete(idx);
        // inform user of deletion
        String response = ui.showDeleteMessage(task, taskList.count());
        // update hard disk
        storage.writeToDisk(taskList);
        return new CommandResponse(response, super.isExit());
    }
}
