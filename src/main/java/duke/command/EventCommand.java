package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Class representing user command to add new Event into list of Tasks.
 */
public class EventCommand extends Command {
    private Event newTask;

    /**
     * Class constructor that initializes the description, date and timing of new
     * Event to be added.
     *
     * @param description Description of Event.
     * @param date Date of Event to attend.
     * @param timing Timing of Event to attend.
     * @return Response to be sent to the GUI.
     */
    public EventCommand(String description, String date, String timing) throws DukeException {
        this.newTask = new Event(description, date, timing);
    }

    /**
     * Executes the command to add new Event and returns Duke's response.
     *
     * @param taskList List of Tasks to be modified by command.
     * @param ui Ui object to be called by the command.
     * @param storage Storage object to be called by the command.
     */
    public Response execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(newTask);
        storage.setChangedTrue();
        return ui.getGotItAddedResponse(newTask);
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return False as not exit command.
     */
    public boolean isExit() {
        return false;
    }
}
