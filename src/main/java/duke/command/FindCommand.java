package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    public FindCommand(String searchString) {
        super(searchString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksWithKeyword = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(args)) {
                tasksWithKeyword.add(task);
            }
        }

        ui.printFoundTasks(tasksWithKeyword);
    }
}
