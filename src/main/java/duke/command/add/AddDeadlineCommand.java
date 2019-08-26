package duke.command.add;

import duke.command.Command;
import duke.storage.Storage;

import duke.exception.DukeDeadlineException;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;
import java.util.Date;

public class AddDeadlineCommand extends Command {

    String detail;
    String datetime;

    public AddDeadlineCommand(String detail, String datetime) {
        this.detail = detail;
        this.datetime = datetime;
    }

    @Override
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
    public boolean isExit() {
        return false;
    }

}
