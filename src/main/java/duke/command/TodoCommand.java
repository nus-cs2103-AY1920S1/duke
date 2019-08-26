package duke.command;

import duke.DukeUtil;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.TaskUtil;
import duke.task.TodoTask;

class TodoCommand extends AddTaskCommand {
    public TodoCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.todo;
    }

    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        String description = DukeUtil.concatStrings(commandArgs, " ");
        TaskUtil.validateTaskDescription(description);
        taskToAdd = new TodoTask(description);
    }
}
