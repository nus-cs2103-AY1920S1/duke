package duke.command;

import duke.exception.DukeException;

import duke.ui.Ui;

import duke.task.TaskList;
import duke.task.Task;

import duke.storage.Storage;

import java.util.List;

public class DoneCommand extends Command {

    String command;

    /**
     * Constructor to create an DoneCommand object.
     *
     * @param command User's command.
     */
    public DoneCommand(String command) {
        this.command = command;
    }

    /**
     * Get the TaskList object to mark the task in the list as done.
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
        List<Task> tasks = taskList.getTasks();
        try {
            String[] done = command.split(" ");
            int number = Integer.parseInt(done[1]);
            if (tasks.get(number - 1).isCompleted()) {
                throw new DukeException("OOPS!!! The task is already marked as done.");
            } else {
                response = taskList.doneTask(number, ui);
                return response;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number does not exist.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Enter a valid task number.");
        }
    }
}
