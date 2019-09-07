package duke.command;

import duke.core.DukeResponder;
import duke.exception.CannotSaveTasksException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.task.Event;
import duke.task.Task;

import java.util.Date;

public class EventCommand extends Command {

    private final String description;
    private final Date at;

    public EventCommand(String description, Date at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public String execute(TaskList tasks, DukeResponder responder, Storage storage) {
        try {
            Task task = new Event(this.description, this.at);
            tasks.add(task);
            storage.saveTasks(tasks);
            return responder.getTaskAddedMessage(task, tasks);
        } catch (CannotSaveTasksException e) {
            return responder.getErrorMessage(e);
        }
    }

}
