package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

import static duke.ui.Messages.LIST_NO_TASKS;
import static duke.ui.Messages.LIST_TASKS;

public class ListCommand extends Command {
    @Override
    protected void check(TaskList tasks) {
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        check(tasks);
        CommandResult result = new CommandResult();
        if (tasks.size() == 0) {
            result.addMessages(LIST_NO_TASKS);
        } else {
            result.addMessages(LIST_TASKS);
            result.addMessages(tasks.toString().split("\\n"));
        }
        return result;
    }
}
