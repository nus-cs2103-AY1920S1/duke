package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor of AddCommand which executes add functionality.
     *
     * @param task the new task which needs to be added to the task list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Tells whether this is an exit duke.command.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the new task into task list.
     *
     * @param taskList The list of duke.tasks maintained in duke.Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the duke.tasks
     * @throws DukeException when the duke.command is invalid
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (task == null) {
            throw new DukeException("No task to be added to the list");
        }
        taskList.addTask(task);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:");
        sb.append("\n  " + task);
        sb.append("\nNow you have " + taskList.getSize() + " tasks in the list.");
        ui.print(sb.toString());
    }
}
