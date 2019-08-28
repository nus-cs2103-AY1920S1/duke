package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    private Task newTask;

    public DeadlineCommand(String description, String date, String timing) {
        this.newTask = new Deadline(description, date, timing);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGotIt(newTask);
        taskList.addTask(newTask);
        ui.printNumTasks();
        storage.setChangedTrue();
    }

    public boolean isExit() {
        return false;
    }
}
