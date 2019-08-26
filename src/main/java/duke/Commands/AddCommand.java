package duke.Commands;

import duke.DirectProcessor.Ui;
import duke.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.DirectProcessor.TaskList;
import duke.Tasks.Todo;

public class AddCommand extends Command {

    private String taskType;

    public AddCommand(String taskType, String taskName) {
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public AddCommand(String taskType, String taskName, String taskTime) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskTime = taskTime;
    }

    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException {
        Task toAdd;
        if (taskType.equals("T")) {
            toAdd = new Todo(taskName);
        } else if (taskType.equals("E")) {
            toAdd = new Event(taskName, taskTime);
        } else {
            toAdd = new Deadline(taskName, taskTime);
        }
        tl.addTask(toAdd);
        ui.showAddMessage(toAdd);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
