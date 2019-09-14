package command;

import converter.StringDateConverter;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

import java.text.ParseException;
import java.util.Date;

/**
 * Represent an event to be added.
 */
public class EventCommand extends Command {
    private String[] details;

    /**
     * Initializes EventCommand with event description and date of event.
     *
     * @param details contains event description and date of event
     */
    public EventCommand(String[] details) {
        this.details = details;
    }

    /**
     * Adds event to task list.
     * Print messages to notify users event has
     * been added to task list.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringDateConverter converter = new StringDateConverter();
            Date at = converter.convertStringToDate(details[1].trim());
            tasks.getTasks().add(new Event(details[0].trim(), at));
            ui.showEventCommand(tasks);
        } catch (ParseException | IndexOutOfBoundsException e) {
            ui.showLoadingError("Please enter a valid date according to dd/MM/yyyy HHmm pattern."
                    + " Time is in 24-hour format. E.g 11:30pm is 2330 and 12:30am is 0030.");
        }
    }
}
