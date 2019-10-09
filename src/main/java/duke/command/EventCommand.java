package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;

/**
 * EventCommand class.
 */
public class EventCommand extends Command {
    private String description;
    private String at;

    /**
     * Constructs a EventCommand object.
     *
     * @param description Description of event.
     * @param at Timing of event.
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) throws DukeException {
        return taskList.addNewTask(new Event(this.description, this.at));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
