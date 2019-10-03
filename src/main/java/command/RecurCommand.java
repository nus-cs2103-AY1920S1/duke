package command;

import exception.DukeException;
import filewriter.Storage;
import task.Recurrence;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class RecurCommand extends Command {
    int index;
    String unitTime;
    int quantity;

    /**
     * Constructor for EditCommand.
     *
     * @param details include index of Task in TaskList to set as recurring, type of unit time, and amount of unit time.
     */
    public RecurCommand(String details) throws DukeException {
        try {
            super.type = FullCommand.RECUR;
            String[] detailsArr = details.split(" ");
            this.index = Integer.parseInt(detailsArr[0]);
            if (detailsArr.length == 3) {
                this.quantity = Integer.parseInt(detailsArr[1]);
                if (quantity == 0) {
                    throw new DukeException("Quantity cannot be 0.");
                }
                this.unitTime = detailsArr[2];
            } else if (detailsArr.length != 1) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Give instructions in the format:"
                    + System.lineSeparator() + "Recur (index)"
                    + System.lineSeparator() + " for Todo type tasks or"
                    + System.lineSeparator() + "Recur (index) (quantity) (unit time)"
                    + System.lineSeparator() + " for Event and Deadline type tasks.");
        }
    }

    /**
     * Used tp check if command is an ExitCommand.
     *
     * @return false as command is an RecurCommand.
     */
    public boolean isExit() {
        assert (!super.type.getName().equals("bye"));
        return false;
    }

    /**
     * Marks the (index)th task in TaskList as done.
     *
     * @param tasks   current TaskList object used in this instance of Duke..
     * @param ui      Instance of user interface to print feedback to user.
     * @param storage updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException Thrown when index == 0, or when index is larger than number of Task(s) in TaskList,
     *                       or when index of Task to markAsComplete is not properly presented.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(index - 1);
            if (task.getStatusIcon().equals("+")) {
                throw new DukeException("Can only convert uncompleted Task to recurring tasks.");
            }
            if (task instanceof Todo) {
                ((Todo) task).setAsRecurring();
                ui.showRecurMessage(task);
            } else {
                if (unitTime == null | quantity == 0) {
                    throw new DukeException("Give instructions in the format:"
                            + System.lineSeparator() + "Recur (index) (quantity) (unit time)"
                            + System.lineSeparator() + " for Event and Deadline type tasks.)");
                }
                Recurrence recurrence = (Recurrence) task;
                recurrence.setAsRecurring(unitTime, quantity);
                ui.showRecurMessage(recurrence);
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a single integer for index of task to recur.");
        }
    }
}
