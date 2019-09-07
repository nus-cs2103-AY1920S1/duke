package duke.command;

import duke.core.DukeResponder;
import duke.exception.CannotSaveTasksException;
import duke.util.Storage;
import duke.util.TaskList;

public class DoneCommand extends Command {

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, DukeResponder responder, Storage storage) {
        try {
            tasks.get(this.index - 1).markAsDone();
            storage.saveTasks(tasks);
            return responder.getTaskDoneMessage(tasks.get(this.index - 1));
        } catch (CannotSaveTasksException e) {
            return responder.getErrorMessage(e);
        }
    }

}
