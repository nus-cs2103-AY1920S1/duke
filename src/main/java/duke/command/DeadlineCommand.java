package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Class representing a command to add a new Deadline to the list.
 */
public class DeadlineCommand extends Command {
    private Deadline newTask;

    /**
     * Class constructor that initializes the description, date and timing of new
     * Deadline to be added.
     *
     * @param description Description of deadline.
     * @param date Date of Deadline to be completed by.
     * @param timing Timing of Deadline to be completed by.
     * @return Response to be sent to the GUI.
     */
    public DeadlineCommand(String description, String date, String timing) throws DukeException {
        this.newTask = new Deadline(description, date, timing);
    }

    /**
     * Executes the command to add the new Deadline and returns Duke's response.
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
