package commands;


import exception.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Class for the command that deletes a task from the tasklist.
 */
public class DeleteCommand extends IndexingCommand {

    /**
     * Constructs the delete command.
     *
     * @param taskNum the index of the task to be deleted
     */
    public DeleteCommand(int taskNum) {
        this.taskNumber = taskNum;
    }

    /**
     * Indicates whether this is an exit command.
     *
     * @return false
     */
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Executes the delete command.
     * Given task will be deleted from the tasklist at the given index position.
     * A completion message will be displayed after successful deletion.
     *
     * @param tasks tasklist that stores all the tasks
     * @param ui ui object that deals with user interaction
     * @param storage storage object that deals with writing into the text file
     * @return string representation of the command response
     * @throws DukeException exception is thrown when the index falls outside the number of tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isTaskNumOutOfRange(taskNumber, tasks.getNumOfTask())) {
            throw new DukeException("List only has " + tasks.getNumOfTask() + " items.");
        } else {
            Task curTask = tasks.getTaskByIndex(taskNumber - 1);
            tasks.deleteTaskByIdx(taskNumber - 1);
            storage.updateFile(tasks);
            return ui.showResultOfCommand("     Noted. I've removed this task:\n       " + curTask
                    + "\n     Now you have " + tasks.getNumOfTask() + " tasks in the list.");

        }
    }

}
