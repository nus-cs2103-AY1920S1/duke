package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    private static final String DUKE_FOUND_TASKS = "Here are the matching tasks in your list:";
    private static final String DUKE_NO_FOUND_TASKS = "There were no matching tasks for the keyword: ";

    private static final String ERROR_MISSING_KEYWORD = "☹ OOPS!!! Please include a phrase to search for.";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeIllegalArgumentException {
        if (keyword.isEmpty()) {
            throw new DukeIllegalArgumentException(ERROR_MISSING_KEYWORD);
        }
        List<Task> foundTasks = taskList.findAllTasksContaining(this.keyword);
        ui.printToUser(listTasks(foundTasks));
    }

    private String[] listTasks(List<Task> taskList) {
        if (taskList.size() == 0) {
            return new String[] { DUKE_NO_FOUND_TASKS, String.format("  \"%s\"", this.keyword) };
        }

        List<String> tasks = new ArrayList<>();
        tasks.add(DUKE_FOUND_TASKS);
        for (int i = 0; i < taskList.size(); i++) {
            tasks.add(String.format("  %d.%s",
                    i + 1,
                    taskList.get(i).getStatus()));
        }
        return tasks.toArray(new String[0]);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
