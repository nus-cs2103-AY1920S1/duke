package duke.command;

import duke.exception.DukeException;

import duke.ui.Ui;

import duke.task.Task;
import duke.task.TaskList;

import duke.storage.Storage;

import java.util.List;

public class DeleteCommand extends Command {

    String command;

    /**
     * Constructor to create an DeleteCommand object.
     *
     * @param command User's command.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Get the TaskList object to delete the task in the list.
     *
     * @param taskList TaskList object containing the current tasks list.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null || ui != null || storage != null :
                "TaskList, Ui and Storage objects cannot be null";

        String response = "";
        try {
            List<Task> tasks = taskList.getTasks();
            String[] done = command.split(" ");
            int number = Integer.parseInt(done[1]);
            Task task = tasks.get(number - 1);
            response = taskList.deleteTask(number, task, ui);
            return response;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number does not exist.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Enter a valid task number.");
        }
    }
}
