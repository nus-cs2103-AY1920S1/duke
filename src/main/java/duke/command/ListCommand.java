package duke.command;

import duke.todo.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {
    /**
     * Returns the task type.
     *
     * @return List.
     */
    public String getTaskType() {
        return "list";
    }

    public int getIndex() {
        return 0;
    }

    public String getDescription() {
        return "";
    }

    public String getDate() {
        return "";
    }

    public String getKeyword() {
        return "error";
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.reportList(tasks.generateListInString());
    }
}
