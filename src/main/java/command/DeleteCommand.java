package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Command to remove Task from Task List.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor for DeleteCommand.
     * @param index the index of the task to delete n the Task List.
     */
    public DeleteCommand(int index) {
        super.type = FullCommand.DELETE;
        this.index = index;
    }

    /**
     * Used to check if command is an ExitCommand.
     * @return false as command is a DeleteCommand.
     */
    public boolean isExit() {
        assert(!super.type.getName().equals("bye"));
        return false;
    }

    /**
     * Removes the current (index)th task from the task list.
     * @param tasks Current TaskList object used in this instance of Duke. Removes specified task from tasks.schedule.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath.
     * @throws DukeException Thrown when index == 0, or when index is larger than number of Task(s) in TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task removedTask = tasks.remove(index);
            ui.readDelete(removedTask, tasks.taskNum);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a single integer for index of task to delete.");
        }
    }
}
