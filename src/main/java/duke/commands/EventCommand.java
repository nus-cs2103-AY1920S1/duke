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
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addEvent(event);
            ui.showMessage(ui.showAddedMessage(taskMessage, tasks.getTasksSize()));
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
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