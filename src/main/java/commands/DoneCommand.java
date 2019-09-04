package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DoneCommand extends Command {
    int taskNumToMark;

    public DoneCommand(int taskNumToMark) {
        this.taskNumToMark = taskNumToMark;
    }

    /**
     * This method is used to mark a task in the list as completed.
     *
     * @return duke's response after marking the task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.getTask(taskNumToMark).isDone()) {
            tasks.doneTask(taskNumToMark);
            storage.updateList(tasks.getList());
            return ui.print("Nice! I've marked this task as done: " + "\n" + tasks.getList().get(taskNumToMark - 1));
        }
        return ui.print("Error, task already completed!");

    }

}
