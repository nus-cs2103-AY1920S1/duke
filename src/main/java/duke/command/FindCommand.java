package duke.command;

import duke.todo.TaskList;
import duke.ui.Ui;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String getTaskType() {
        return "find";
    }

    public int getIndex() {
        return 0;
    }

    public String getTask() {
        return "";
    }

    public String getDate() {
        return "";
    }

    public String getKeyword() {
        return keyword;
    }

    public void execute(TaskList tasks, Ui ui) {
        String taskFound = tasks.findTask(getKeyword());
        ui.reportFound(taskFound);
    }
}
