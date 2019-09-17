package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Event;
import duke.ui.UI;

import java.io.IOException;

/**
 * Event command.
 */
public class EventCommand extends Command {
    Event event;

    /**
     * Initialise with event task.
     *
     * @param e event
     */
    public EventCommand(Event e) {
        this.event = e;
    }

    /**
     * Adds event to Task List and saves it in Storage.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     * @return Added message or error message
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addTask(event);
            storage.save(tasks.getTasks());
            return ui.getAddedMessage(taskMessage, tasks.getTasksSize());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Does not exit.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}