package duke.command;

import duke.DukeInvalidArgumentException;
import duke.DukeUtil;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.task.TaskUtil;
import duke.task.TodoTask;

public class TodoCommand extends AddTaskCommand {
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
