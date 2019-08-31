package duke.command;

import duke.exception.DukeException;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.util.Ui;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.addNewTask(new ToDo(this.description));
    }

    public boolean isExit() {
        return false;
    }
}
