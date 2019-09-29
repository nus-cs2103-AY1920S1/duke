package duke.command;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(this.keyword);
        if (matchingTasks.isEmpty()) {
            ui.showError(new DukeException(ExceptionType.NO_MATCHING_TASKS));
        } else {
            ui.showTasks(matchingTasks);
        }
    }
}
