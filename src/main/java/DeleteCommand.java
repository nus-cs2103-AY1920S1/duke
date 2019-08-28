import java.lang.Integer;
import java.io.IOException;

/**
 * Encapsulates command for deleting task from the task list.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 3
 */
public class DeleteCommand extends Command {

    /** The serial number (1-indexed) of the task to be deleted*/
    private String taskNumber;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (tasks.isEmpty()) {
            // if the user is trying this command on an empty task list
            ui.showEmptyListError();
        } else {
            try {
                int taskIndex = Integer.parseInt(taskNumber) - 1; // 1 indexed
                // retrieve task to be removed, remove it, and inform the user
                Task taskToRemove = tasks.get(taskIndex);
                tasks.remove(taskIndex);

                // message
                ui.showDeleteTaskMessage(taskToRemove, tasks.size());

                // update storage
                storage.update(tasks);
            } catch (IndexOutOfBoundsException exceptionOne) {
                ui.showIndexOutOfBoundsError();
            } catch (NumberFormatException exceptionTwo) {
                ui.showInvalidIndexError();
            }
        }
    }
}
