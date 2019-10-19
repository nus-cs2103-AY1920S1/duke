package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_CLEAR;

public class ClearCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clearTasks();
        storage.save(tasks);
        tasks.commit();
        return MESSAGE_CLEAR;
    }
}
