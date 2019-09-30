package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

import java.io.IOException;

public class PriorityCommand extends Command {
    private int taskNumber;
    private String priority;

    public PriorityCommand(int taskNumber, String priority) {
        this.taskNumber = taskNumber;
        this.priority = priority;
    }
    public String execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        String result = tasks.setPriority(this.taskNumber, this.priority);
        storage.save(tasks);
        return result;
    }
}
