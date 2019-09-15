package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddCommand extends TaskCommand {
    private static final String outputNoTask = "No task to be added to the list\n";
    private static final String outputAddTask = "Got it. I've added this task:\n";
    private static final String outputStringPre = "Now you have ";
    private static final String outputStringPost = " tasks in the list.\n";
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (task == null) {
            throw new DukeException(outputNoTask);
        }
        taskList.addTask(task);
        StringBuilder sb = new StringBuilder(outputAddTask);
        sb.append(task + "\n");
        sb.append(outputStringPre + taskList.getSize() + outputStringPost);
        return ui.print(sb.toString());
    }
}
