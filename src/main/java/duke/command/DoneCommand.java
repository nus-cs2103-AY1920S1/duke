package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {

    private int taskNo;

    /**
     * Creates a new DoneCommand object.
     *
     * @param taskNo Task Number of task.
     */
    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes the current command.
     *
     * @param ui       Ui object.
     * @param storage  Storage object.
     * @param taskList TaskList object.
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        Task task = taskList.done(taskNo);

        if (task != null) {
            storage.save(taskList.getTaskList());
            return ui.returnOutput("Nice! I've marked this task as done: \n  " + task);
        }

        return null;
    }
}
