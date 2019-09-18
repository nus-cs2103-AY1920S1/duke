package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import exceptions.DukeException;

import tasks.Task;

import java.util.ArrayList;

/**
 * DoneCommand is a class that marks the specified task
 * item in the list of tasks as done.
 * These items can be ToDo, Event or Deadline tasks.
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public DoneCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Marks the specified task as completed.
     * The number of the task to be marked is specified
     * by the user via the user input.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        if (commandArr.length <= 1) {
            throw new DukeException(ui.getMissingTaskNumMsg());
        }
        int taskDoneIndex;
        try {
            taskDoneIndex = Integer.parseInt(commandArr[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ui.getInvalidNumFormatMsg());
        }
        assert taskDoneIndex >= 0 : "taskDoneIndex must be non-negative";
        if (Command.checkValidTaskNumber(taskDoneIndex, taskLst)) {
            taskLst.get(taskDoneIndex).setDone();
            return ui.getSuccessfulDoneMsg(taskDoneIndex, taskLst);
        } else {
            throw new DukeException(ui.getInvalidTaskNumMsg(taskLst));
        }

    }

}

