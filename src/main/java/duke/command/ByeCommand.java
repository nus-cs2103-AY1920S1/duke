package duke.command;

import duke.todo.TaskList;
import duke.ui.Ui;

/**
 *
 */
public class ByeCommand implements Command {
    /**
     * Returns task type.
     *
     * @return Bye.
     */
    public String getTaskType() {
        return "bye";
    }

    public int getIndex() {
        return 0;
    }

    public String getDescription() {
        return "error";
    }

    public String getKeyword() {
        return "error";
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.reportBye();
    }
}
