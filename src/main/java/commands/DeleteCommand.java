package commands;

import java.util.ArrayList;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import exceptions.DukeException;

import tasks.Task;

/**
 * DeleteCommand is a class that removes the specified
 * task item from the list of tasks. These items
 * can be ToDo, Event or Deadline tasks.
 */
public class DeleteCommand extends Command {

    /**
     *  Constructor for DeleteCommand.
     *  Boolean isExit is set to false because
     *  program should not terminate after command is executed.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public DeleteCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    /**
     * Removes the specified task from the list of tasks.
     * The number of the task to be removed is specified
     * in the user input.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        if (commandArr.length <= 1) {
            throw new DukeException("\u2639 A task number has to be specified.");
        }
        int delTaskIndex;
        delTaskIndex = Integer.parseInt(commandArr[1]) - 1;
        assert delTaskIndex >= 0 : "delTaskIndex must be non-negative";
        if (Command.checkValidTaskNumber(delTaskIndex, taskLst)) {
            Task deletedTask = taskLst.remove(delTaskIndex);
            return String.format("Noted. I've removed this task:\n"
                            + "%s\nNow you have %d tasks in the list.", deletedTask, taskLst.size());
        } else {
            String msg = String.format("\u2639 You have entered an invalid task number!" +
                    " Please enter a number from between 1 to %d", taskLst.size());
            throw new DukeException(msg);
        }
    }

}
