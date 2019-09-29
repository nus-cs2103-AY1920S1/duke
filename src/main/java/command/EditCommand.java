package command;

import exception.DukeException;
import filewriter.Storage;
import task.Recurrence;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class EditCommand extends Command {
    int index;

    /**
     * Constructor for EditCommand.
     * @param index index of Task in TaskList to mark as done.
     */
    public EditCommand(int index) {
        super.type = FullCommand.DONE;
        this.index = index;
    }

    /**
     * Used tp check if command is an ExitCommand.
     * @return false as command is an EditCommand.
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
            if (index == -1) {
                throw new IndexOutOfBoundsException();
            }
            Task completedTask = tasks.complete(index);
            ui.readDone(completedTask);
            if (completedTask instanceof Recurrence) {
                if (((Recurrence) completedTask).isRecurring) {
                    Task nextTask = ((Recurrence) completedTask).getRecurrence();
                    tasks.addTask(nextTask);
                    ui.showUpdateMessage(nextTask);
                }
            } else if (completedTask instanceof Todo) {
                if (((Todo) completedTask).isRecurring) {
                    Task nextTask = ((Todo) completedTask).getRecurrence();
                    tasks.addTask(nextTask);
                    ui.showUpdateMessage(nextTask);
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a single integer index of task to delete.");
        }
    }
}
