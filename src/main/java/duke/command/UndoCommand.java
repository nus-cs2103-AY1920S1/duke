package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_INDEX;
import static duke.ui.Message.MESSAGE_NO_TASKS_IN_LIST;
import static duke.ui.Message.MESSAGE_UNDO;
import static duke.ui.Message.concatLines;

public class UndoCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.undo();
        storage.save(tasks);

        if (tasks.isEmpty()) {
            return concatLines(MESSAGE_UNDO, MESSAGE_NO_TASKS_IN_LIST);
        } else {
            return concatLines(MESSAGE_UNDO, MESSAGE_INDEX, tasks.toString());
        }
    }
}
