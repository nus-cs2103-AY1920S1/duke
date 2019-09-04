package duke.commands;

import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Storage;
import duke.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deadline = new Deadline(this.description, this.by);
        taskList.addTask(deadline);
        ui.showAddTask(deadline, taskList);
        storage.saveData(taskList);
    }
}
