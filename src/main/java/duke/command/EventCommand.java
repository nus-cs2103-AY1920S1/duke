package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {

    private Event newTask;

    public EventCommand(String description, String date, String timing) {
        this.newTask = new Event(description, date, timing);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGotIt(newTask);
        taskList.addTask(newTask);
        ui.printNumTasks();
    }

    public boolean isExit() {
        return false;
    }
}
