package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_INDEX;
import static duke.ui.Message.MESSAGE_NO_TASKS_IN_LIST;
import static duke.ui.Message.MESSAGE_REDO;
import static duke.ui.Message.concatLines;

public class RedoCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.redo();
        storage.save(tasks);

        if (tasks.isEmpty()) {
            return concatLines(MESSAGE_REDO, MESSAGE_NO_TASKS_IN_LIST);
        } else {
            return concatLines(MESSAGE_REDO, MESSAGE_INDEX, tasks.toString());
        }
    }
}
