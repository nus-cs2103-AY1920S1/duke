package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a EventCommand object in charge of adding a event task into the task list.
 */

public class EventCommand extends Command {

    private final String activity;
    private final String time;

    /**
     * Creates an EventCommand object.
     * @param fullCommand String of full, valid command
     * @param activity String of the event activity name.
     * @param time String of the time of the event.
     */
    public EventCommand(String fullCommand, String activity, String time) {
        super(fullCommand);
        this.activity = activity;
        this.time = time;
    }

    @Override
    /**
     * Adds an event task into the taskList and updates file in storage.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage the storage object that deals with saving and loading task lists.
     * @return String of duke's response message.
     * @throws DukeException when storage file is not found.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String message = tasks.addEvent(activity, time, false);
        storage.updateFile(tasks);
        return message;
    }
}