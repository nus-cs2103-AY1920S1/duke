package duke.command;

import duke.core.DukeResponder;
import duke.exception.CannotSaveTasksException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, DukeResponder responder, Storage storage) {
        try {
            Task task = new Todo(this.description);
            tasks.add(task);
            storage.saveTasks(tasks);
            return responder.getTaskAddedMessage(task, tasks);
        } catch (CannotSaveTasksException e) {
            return responder.getErrorMessage(e);
        }
    }

}
