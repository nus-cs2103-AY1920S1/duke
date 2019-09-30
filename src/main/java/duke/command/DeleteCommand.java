package duke.command;

import duke.Storage;
import duke.task.TaskList;

import java.util.List;

public class DeleteCommand extends DoneCommand {
    public DeleteCommand(TaskList tasks, Storage storage) {
        super(tasks, storage, index -> List.of("Noted. I've removed this task:", "  " + tasks.remove(index),
                "Now you have " + tasks.size() + " tasks in the list."));
    }
}
