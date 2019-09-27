package command;

import exception.DukeException;
import filewriter.Storage;
import task.Recurrence;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class RevertCommand extends Command{
    int index;

    /**
     * Constructor for EditCommand.
     * @param index include index of Task in TaskList to revert back to non-recurring.
     */
    public RevertCommand(int index) {
        super.type = FullCommand.REVERT;
        this.index = index;
    }

    /**
     * Used tp check if command is an ExitCommand.
     * @return false as command is an RevertCommand.
     */
    public boolean isExit() {
        assert(!super.type.getName().equals("bye"));
        return false;
    }

    /**
     * Marks the (index)th task in TaskList as done.
     * @param tasks current TaskList object used in this instance of Duke..
     * @param ui Instance of user interface to print feedback to user.
     * @param storage updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException Thrown when index == 0, or when index is larger than number of Task(s) in TaskList,
     *                       or when index of Task to markAsComplete is not properly presented.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(index);
            if (task instanceof Todo) {
                throw new DukeException("Todo tasks are not recurring.");
            }
            if (task instanceof Recurrence) {
                if (!((Recurrence) task).isRecurring) {
                    throw new DukeException("Task is not recurring.");
                }
            }
            Recurrence recurrence = (Recurrence) task;
            recurrence.revert();
            ui.showRevertMessage(task);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a single integer index of task to delete.");
        }
    }
}
