package duke.command.add;

import duke.command.Command;
import duke.exception.DukeEventException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.util.Date;

public class AddEventCommand extends Command {

    /** Details of the event. */
    private String detail;
    /** Date-Time starting from. */
    private String datetimeFrom;
    /** Date-Time ending with. */
    private String datetimeTo;

    /**
     * Constructs the AddEventCommand object.
     * @param detail Name of the Event
     * @param datetimeFrom Starting from
     * @param datetimeTo Ending with
     */
    public AddEventCommand(String detail, String datetimeFrom, String datetimeTo) {
        this.detail = detail;
        this.datetimeFrom = datetimeFrom;
        this.datetimeTo = datetimeTo;
    }

    @Override
    /**
     * Adds new Event task.
     * @param tasks The current TaskList object
     * @param ui The current Ui object
     * @param storage The current Storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Date datetimeFromDate = super.DATE_FORMAT.parse(datetimeFrom);
            Date datetimeToDate = super.DATE_FORMAT.parse(datetimeTo);
            Event newEvent = new Event(detail, datetimeFromDate, datetimeToDate);
            tasks.add(newEvent);
            duke.ui.Ui.printAddSuccess(tasks.getTasks(), newEvent);
        } catch (ParseException dfe) {
            ui.exposeError(new DukeEventException(DukeEventException.FORMAT_ERROR_MSG).getMessage());
        }
    }

    @Override
    /**
     * Returns if this is an exiting command.
     * @return Whether this command exits the application
     */
    public boolean isExit() {
        return false;
    }

}
