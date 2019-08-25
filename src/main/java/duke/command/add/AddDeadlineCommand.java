package duke.command.add;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {

    String detail;
    String datetime;

    public AddDeadlineCommand(String detail, String datetime) {
        this.detail = detail;
        this.datetime = datetime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Deadline newDeadline = new Deadline(detail, datetime);
        tasks.add(newDeadline);
        ui.printAddSuccess(tasks.getTasks(), newDeadline);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
