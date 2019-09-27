package slave.command;

import slave.elements.DateTime;
import slave.elements.Tags;
import slave.elements.Ui;
import slave.elements.TaskList;

import slave.exception.KappaException;

import slave.task.Event;

/**
 * Represents a command which adds an event into storage and task list.
 */
public class AddEventCommand extends Command {

    private String task;
    private String date;
    private Tags tags;

    /**
     * Constructor (date doesn't fit the DD/MM/YYYY HHMM format).
     *
     * @param task Event description.
     * @param date Date description.
     * @param tags Tags.
     */
    public AddEventCommand(String task, String date, Tags tags) {
        this.commandType = CommandType.ADDEVENT;
        this.task = task;
        this.date = date;
        this.tags = tags;
    }

    /**
     * Constructor (date fits into DD/MM/YYYY HHMM format).
     *
     * @param task Event description.
     * @param date Date description.
     * @param tags Tags.
     * @throws KappaException Throws invalid date exception of DD/MM/YYYY HHMM format.
     */
    public AddEventCommand(String task, DateTime date, Tags tags) throws KappaException {
        this.commandType = CommandType.ADDEVENT;
        this.task = task;
        this.date = date.convertToString();
        this.tags = tags;
    }

    /**
     * Executes the command by adding event task to list and print to user.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws KappaException For error in adding to TaskList.
     * @return String containing Kappa's formatted dialog.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws KappaException {
        Event eventTask = new Event(this.task, tasks.getSize() + 1, this.date, this.tags);
        tasks.addToList(eventTask);
        return ui.printAddEventCommand(eventTask, tasks);
    }
}
