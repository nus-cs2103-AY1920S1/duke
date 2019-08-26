package duke.command.add;

import duke.command.Command;
import duke.exception.DukeEventException;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.util.Date;

public class AddEventCommand extends Command {

    String detail;
    String datetimeFrom;
    String datetimeTo;

    public AddEventCommand(String detail, String datetimeFrom, String datetimeTo) {
        this.detail = detail;
        this.datetimeFrom = datetimeFrom;
        this.datetimeTo = datetimeTo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
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
    public boolean isExit() {
        return false;
    }

}
