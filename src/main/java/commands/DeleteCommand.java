package commands;

import tasks.Task;
import storage.Storage;
import util.TaskList;
import ui.Ui;

import java.lang.Integer;
import java.io.IOException;

/**
 * Encapsulates command for deleting task from the task list.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class DeleteCommand extends UndoableCommand {

    /** The serial number (1-indexed) of the task to be deleted. */
    private String taskNumber;

    /** The task that was deleted by this command. */
    private Task task;

    /**
     * Initialises a command for deleting the specified task.
     *
     * @param imperative the term used to identify command type
     * @param content string representing the index of the task to be deleted
     */
    public DeleteCommand(String imperative, String content) {
        super(imperative);
        this.taskNumber = content;
    }

    /**
     * Executes the delete command by deleting the specified task from the
     * list, sending a suitable message to the user interface, and asking
     * the storage handler to update the task list on the hard disk.
     *
     * @param tasks the task list the task is to be added to
     * @param ui the user interface associated with this run of Duke
     * @param storage the storage handler associated with this run of Duke
     * @throws IOException when file the list is to be written to is not found
     * @return Duke's response to the user command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (tasks.isEmpty()) {
            // if the user is trying this command on an empty task list
            return ui.showEmptyListError();
        } else {
            try {
                int taskIndex = parseStringToIntIndex(this.taskNumber);
                assert taskIndex >= 0;

                // retrieve task to be removed and remove it
                Task taskToRemove = tasks.get(taskIndex);
                this.task = taskToRemove;
                assert taskToRemove != null;
                tasks.remove(taskIndex);

                // update storage
                storage.update(tasks);

                // inform user that task has been removed
                return ui.showDeleteTaskMessage(taskToRemove, tasks.size());

            } catch (IndexOutOfBoundsException exceptionOne) {
                return ui.showIndexOutOfBoundsError();
            } catch (NumberFormatException exceptionTwo) {
                return ui.showInvalidIndexError();
            }
        }
    }

    /**
     * Undo the command by adding the task that was deleted.
     *
     * @param tasks the task list the task is to be added to.
     * @param ui the user interface associated with this run of Duke.
     * @param storage the storage handler associated with this run of Duke.
     * @return Duke's response to the user command.
     * @throws IOException when file the list is to be written to is not found.
     */
    public String undo(TaskList tasks, Ui ui, Storage storage)
            throws IOException {
        assert this.task != null;
        if (this.task != null) {
            tasks.add(this.task);

            storage.update(tasks);

            return ui.showCommandUndoneMessage(tasks);
        } else {
            return ui.showNoCommandToUndoError();
        }
    }
}
