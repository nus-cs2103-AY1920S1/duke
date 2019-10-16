package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;

import static duke.ui.Message.ADD_TASK_MESSAGE;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert super.description != null;
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
        return ui.showTask(deadline, tasks, ADD_TASK_MESSAGE);
    }
}
