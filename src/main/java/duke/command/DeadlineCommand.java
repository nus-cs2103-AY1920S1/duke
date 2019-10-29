package duke.command;

import duke.core.DukeResponder;
import duke.exception.CannotSaveTasksException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.task.Deadline;
import duke.task.Task;

import java.util.Date;

public class DeadlineCommand extends Command {

    private final String description;
    private final Date by;

    public DeadlineCommand(String description, Date by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, DukeResponder responder, Storage storage) {
        try {
            Task task = new Deadline(this.description, this.by);
            tasks.add(task);
            storage.saveTasks(tasks);
            return responder.getTaskAddedMessage(task, tasks);
        } catch (CannotSaveTasksException e) {
            return responder.getErrorMessage(e);
        }
    }

}
