package command;

import converter.StringDateConverter;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.text.ParseException;
import java.util.Date;

/**
 * Represents a deadline task to added.
 */
public class DeadlineCommand extends Command {
    private String[] details;

    /**
     * Initializes DeadlineCommand with deadline description and due date.
     *
     * @param details contains description and due date
     */
    public DeadlineCommand(String[] details) {
        this.details = details;
    }

    /**
     * Adds deadline to task list.
     * Prints messages to notify users deadline has been
     * added to task list
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringDateConverter converter = new StringDateConverter();
            Date by = converter.convertStringToDate(details[1].trim());
            tasks.getTasks().add(new Deadline(details[0].trim(), by));
            ui.showDeadlineCommand(tasks);
        } catch (ParseException | IndexOutOfBoundsException e) {
            ui.showLoadingError("Please enter a valid date according to dd/MM/yyyy HHmm pattern."
                    + " Time is in 24-hour format. E.g 11:30pm is 2330 and 12:30am is 0030.");
        }
    }
}
