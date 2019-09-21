package bot.duke.command.add;

import java.text.ParseException;
import java.util.Date;

import bot.duke.command.Command;
import bot.duke.exception.DukeDeadlineException;
import bot.duke.storage.Storage;
import bot.duke.task.Deadline;
import bot.duke.task.Task;
import bot.duke.task.TaskList;
import bot.duke.ui.Ui;

public class AddDeadlineCommand extends Command {

    /** Details of the event. */
    private String description;

    /** Date-Time of the Deadline. */
    private String datetime;

    /**
     * Constructs the AddDeadline.
     *
     * @param description   Details of the Deadline
     * @param datetime Deadline of the Task object
     */
    public AddDeadlineCommand(String description, String datetime) {
        this.description = description;
        this.datetime = datetime;
    }

    @Override
    /**
     * Adds new Deadline event.
     * @param tasks The current TaskList object
     * @param ui The current Ui object
     * @param storage The current Storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Date datetimeDate = Task.DATE_FORMAT.parse(datetime);
            Deadline newDeadline = new Deadline(description, datetimeDate);
            assert newDeadline != null;
            tasks.add(newDeadline);
            ui.printAddSuccess(tasks.getTasks(), newDeadline);
        } catch (ParseException e) {
            ui.exposeError(new DukeDeadlineException(DukeDeadlineException.FORMAT_ERROR_MSG).getMessage());
        }
    }

    @Override
    /**
     * Returns whether this is an exiting command.
     * @return Whether this command exits the application
     */
    public boolean isExit() {
        return false;
    }

}
