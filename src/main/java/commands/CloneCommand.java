package commands;

import exception.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Class representing the command that clones a task represented by its index.
 */
public class CloneCommand extends IndexingCommand {

    /**
     * Constructs the clone command.
     *
     * @param taskNum the index of the task to be cloned
     */
    public CloneCommand(int taskNum) {
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
     * Executes the clone command.
     * Given task at the given index will be cloned into the tasklist and added to the tail of
     * the list.
     * A completion message will be displayed after successful cloning.
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
            tasks.addTask(curTask.clone());
            storage.updateFile(tasks);
            return ui.showResultOfCommand("     Noted. I've cloned this task:\n       "
                    + curTask + "\n     Now you have " + tasks.getNumOfTask()
                    + " tasks in the list.");

        }
    }

}
