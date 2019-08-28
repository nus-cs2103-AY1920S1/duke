package duke.command.add;

import duke.command.Command;
import duke.exception.DukeDeadlineException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.util.Date;

public class AddDeadlineCommand extends Command {

    /** Details of the event. */
    private String detail;

    /** Date-Time of the Deadline. */
    private String datetime;

    /**
     * Constructs the AddDeadline.
     * @param detail Details of the Deadline
     * @param datetime Deadline of the Task object
     */
    public AddDeadlineCommand(String detail, String datetime) {
        this.detail = detail;
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
            Date datetimeDate = super.DATE_FORMAT.parse(datetime);
            Deadline newDeadline = new Deadline(detail, datetimeDate);
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
