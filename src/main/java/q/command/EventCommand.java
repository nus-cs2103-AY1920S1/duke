package q.command;

import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;
import q.task.Event;
import q.task.Task;

import java.util.Date;

/**
 * Handles the adding of an event.
 */
public class EventCommand extends AddCommand {
    private String description;
    private Date at;

    /**
     * Constructs an EventCommand.
     *
     * @param description The description of the event.
     * @param at          The date of the event.
     */
    public EventCommand(String description, Date at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Adds an Event of description and at to tasks.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String representing the event.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task event = new Event(description, at);
        tasks.addTask(event);
        storage.save(tasks);
        return ui.showAddTask(event, tasks.getSize());
    }
}
