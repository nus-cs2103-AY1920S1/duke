package commands;

import exception.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Class for the command that marks a given task done given its index.
 */
public class DoneCommand extends IndexingCommand {

    /**
     * Constructs the done command.
     *
     * @param taskNum the index of the task to be marked done
     */
    public DoneCommand(int taskNum) {
        super.taskNumber = taskNum;
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
     * Executes the done command.
     * Given task will be marked done from the tasklist at the index position.
     * A completion message will be displayed after successful execution.
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
            curTask.markDone();
            storage.updateFile(tasks);
            return ui.showResultOfCommand("     Nice! I've marked this task as done: \n     "
                    + curTask);
        }
    }
}
