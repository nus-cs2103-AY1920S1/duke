package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.logic.TaskList;
import duke.ui.Ui;

/**
 * Delete command that inherits from Command, deletes a task from TaskList.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param input input from user.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Deletes a Task from the current TaskList.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] inputArr = input.split(" ");
            //no input number
            if (inputArr.length == 1) {
                throw new DukeException("☹ OOPS!!! Please input a valid number.");
            }
            int num = Integer.valueOf(inputArr[1]);
            //invalid num, will index out of bounds
            Task task = tasks.getTask(num - 1);
            tasks.deleteTask(num - 1);
            ui.setDeleteResponse(task, tasks.getSize());
            storage.save(tasks);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please input a valid number.");
        }

    }
}


