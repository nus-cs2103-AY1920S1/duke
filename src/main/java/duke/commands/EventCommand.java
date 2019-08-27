package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Event;
import java.io.IOException;

/**
 * Event command
 */
public class EventCommand extends Command {
    Event e;

    /**
     * Initialise with event task.
     * @param e
     */
    public EventCommand(Event e) {
        this.e = e;
    }

    /**
     * Adds event to Task List and saves it in Storage.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            String taskMessage = tasks.addEvent(e);
            ui.showAddedMessage(taskMessage, tasks.getTasksSize());
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Does not exit
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}