package commands;

import tasks.Task;
import storage.Storage;
import util.TaskList;
import ui.Ui;

import java.lang.Integer;
import java.io.IOException;

/**
 * Encapsulates command for marking a task as done.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class DoneCommand extends Command {

    /** The serial number (1-indexed) of the task to be marked done. */
    private String taskNumber;

    /**
     * Initialises a command for marking the specified task as done.
     *
     * @param imperative the term used to identify command type
     * @param content string representing the index of the task to be marked done
     */
    public DoneCommand(String imperative, String content) {
        super(imperative);
        this.taskNumber = content;
    }

    /**
     * Executes the command by retreiving the specified task and marking it
     * as done.
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
                int taskIndex = Integer.parseInt(taskNumber) - 1;

                // retrieve task from list, mark as done, and inform the user
                Task taskToMarkDone = tasks.get(taskIndex);
                if (!taskToMarkDone.getIsDone()) {
                    taskToMarkDone.setTaskAsDone(true);

                    // update storage
                    storage.update(tasks);

                    // return message
                    return ui.showTaskDoneMessage(taskToMarkDone);
                } else {
                    return ui.showTaskAlreadyDoneMessage();
                }
            } catch (IndexOutOfBoundsException exceptionOne) {
                return ui.showIndexOutOfBoundsError();
            } catch (NumberFormatException exceptionTwo) {
                return ui.showInvalidIndexError();
            }
        }
    }
}
