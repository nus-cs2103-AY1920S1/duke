package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    int taskNo;

    /**
     * Creates a new DeleteCommand object.
     *
     * @param taskNo Task Number of task.
     */
    public DeleteCommand(int taskNo) {
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
        Task task = taskList.delete(taskNo);

        if (task != null) {
            /*
            ui.printOutput("  " + task,
                "Noted. I've removed this task: ",
                taskList.getTaskList().size());
             */
            storage.save(taskList.getTaskList());
            return ui.returnOutput("  " + task,
                "Noted. I've removed this task: ",
                taskList.getTaskList().size());
        }

        return null;
    }
}
