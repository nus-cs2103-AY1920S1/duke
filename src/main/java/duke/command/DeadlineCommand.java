package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.addNewTask(new Deadline(this.description, this.by));
    }

    public boolean isExit() {
        return false;
    }
}
