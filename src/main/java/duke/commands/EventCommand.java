package duke.commands;

import duke.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Storage;
import duke.Ui;

public class EventCommand extends Command {
    private String description;
    private String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task event = new Event(this.description, this.at);
        taskList.addTask(event);
        ui.showAddTask(event, taskList);
        storage.saveData(taskList);
    }
}
