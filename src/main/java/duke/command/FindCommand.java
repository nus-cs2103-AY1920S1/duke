package duke.command;

import duke.handler.Storage;
import duke.handler.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    protected String keyWord;

    public FindCommand(String fullCommand, String keyWord) {
        this.fullCommand = fullCommand;
        this.keyWord = keyWord;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.clearResponse();
        ui.showMatchingTasksMessage();
        for (Task task : tasks.getTasks()) {
            String description = task.getDescription();
            if (description.contains(keyWord)) {
                int taskIndex = tasks.getTasks().indexOf(task) + 1;
                ui.showMatchingTasks(task, taskIndex);
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
