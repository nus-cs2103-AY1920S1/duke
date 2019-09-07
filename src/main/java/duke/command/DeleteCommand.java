package duke.command;

import duke.core.DukeResponder;
import duke.exception.CannotSaveTasksException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, DukeResponder responder, Storage storage) {
        try {
            Task task = tasks.remove(this.index - 1);
            storage.saveTasks(tasks);
            return responder.getTaskDeletedMessage(task, tasks);
        } catch (CannotSaveTasksException e) {
            return responder.getErrorMessage(e);
        }
    }

}
