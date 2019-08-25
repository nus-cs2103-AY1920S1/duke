package duke.command.add;

import duke.command.Command;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

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
        Event newEvent = new Event(detail, datetimeFrom, datetimeTo);
        tasks.add(newEvent);
        duke.ui.Ui.printAddSuccess(tasks.getTasks(), newEvent);
    }

    ;

    @Override
    public boolean isExit() {
        return false;
    }

}
