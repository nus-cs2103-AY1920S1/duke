package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(String[] description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.description.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String content = description[1];
        String[] actionAndDate = content.split("/by");

        if (actionAndDate.length < 2) {
            throw new DukeException("☹ OOPS!!! The format for deadline is invalid.");
        }
        Deadline deadline = new Deadline(actionAndDate[0], actionAndDate[1]);
        tasks.addTask(deadline);

        storage.save(tasks);
        ui.showTask(deadline, tasks, "     Got it. I've added this task: ");
    }
}
