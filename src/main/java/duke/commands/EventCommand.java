package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    private String description;
    private String at;

    /**
     * Create an event command instance.
     * @param description information about the event
     * @param at date and time of the event in string format
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Create an event.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) throws DukeException {
        TaskList taskList = model.getTaskList();
        Task event = new Event(this.description, this.at);
        taskList.addTask(event);
        ui.showAddTask(event, taskList);
        storage.saveData(model);
    }
}
