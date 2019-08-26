package Commands;

import DirectProcessor.Storage;
import DirectProcessor.Ui;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import DirectProcessor.TaskList;
import Tasks.Todo;

import java.util.ArrayList;

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
    public void execute(TaskList tl, Ui ui) {
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
