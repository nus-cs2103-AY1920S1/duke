package kappa.command;

import kappa.elements.DateTime;
import kappa.elements.Tags;
import kappa.elements.Ui;
import kappa.elements.TaskList;

import kappa.exception.KappaException;

import kappa.task.Event;

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
     * Gets task.
     *
     * @return Task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Gets tags.
     *
     * @return Tags.
     */
    public Tags getTags() {
        return this.tags;
    }

    /**
     * Gets date in string.
     *
     * @return date in string format.
     */
    public String getDate() {
        return this.date;
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
