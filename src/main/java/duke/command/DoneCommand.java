package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {

    int taskNo;

    public DoneCommand(int taskNo){
        this.taskNo = taskNo;
    }

    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task task = taskList.done(taskNo);

        if (task != null) {
            ui.printOutput("Nice! I've marked this task as done: \n  " + task);
            storage.save(taskList.getTaskList());
        }
    }
}
