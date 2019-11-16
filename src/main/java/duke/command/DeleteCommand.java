package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Statistics;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents user's delete commmand to chatbot.
 * The 'DeleteCommand' class supports operators (i) executing the command
 * and (ii) checking if the bot has exited its conversation with the user.
 */
public class DeleteCommand extends Command {

    /**
     * The task number to be deleted.
     */
    int taskNum;

    /**
     * Initializes a new instance of DeleteCommand, with the task number to delete.
     *
     * @param taskNum Task number
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes respective task in taskList and from text file.
     * Prints response in console.
     *
     * @param taskList List of the things user needs to do.
     * @param ui       Interface that interacts with the user.
     * @param storage  Stores the user input in a file.
     * @throws DukeException IOException if there is an error writing or reading file.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.deleteText(taskNum);

        Task removedTask = taskList.deleteTask(taskNum);

        boolean isCompleted = removedTask.getStatusIcon().equals("v");

        if(isCompleted) {
            Statistics.decrementCompleted();
        } else if(!isCompleted){
            Statistics.decrementUncompleted();
        } else {
            throw new DukeException("Deleted task is not properly marked as completed or not");
        }
        storage.updateStatistics();


        return ("Noted. I've removed this task:\n" + removedTask
                + "Now you have " + taskList.size()
                + " tasks in the list.");


    }

    /**
     * Returns a false to indicate program has not exited.
     *
     * @return false Program has not exited.
     */
    public boolean isExit() {
        return false;
    }


}