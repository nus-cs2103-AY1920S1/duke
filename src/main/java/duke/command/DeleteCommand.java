package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from a TaskList.
 */
public class DeleteCommand extends Command {
    /**
     * The number of the task to delete (starting from 1).
     */
    private int taskNumToDelete;

    /**
     * Constructor for DeleteCommand takes in a one-based index task number to delete.
     *
     * @param taskNumToDelete One-based index task number to delete.
     */
    public DeleteCommand(int taskNumToDelete) {
        super();
        this.taskNumToDelete = taskNumToDelete;
    }

    /**
     * Execute this deleteCommand.
     * If the taskNumber provided is out of bounds of the TaskList, a DukeException is thrown.
     *
     * @param tasks   The user's current TaskList
     * @param ui      The ui currently being used by the user
     * @param storage The storage object being used by the user
     * @throws DukeException An error trying to delete a task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskNumToDelete <= 0 || this.taskNumToDelete > tasks.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        Task removedTask = tasks.deleteTaskFromList(this.taskNumToDelete);
        String answer = ("Noted. I've removed this task:"
                + System.lineSeparator()
                + removedTask.getTaskDescription()
                + System.lineSeparator()
                + "Now you have " + tasks.getSize()
                        + ((tasks.getSize() == 1) ? " task" : " tasks") + " in the list.");
        ui.messageUser("Noted. I've removed this task:",
                removedTask.getTaskDescription(),
                "Now you have " + tasks.getSize()
                        + ((tasks.getSize() == 1) ? " task" : " tasks") + " in the list.");
        return answer;
    }
}
