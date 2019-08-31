package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import duke.task.Task;
import duke.task.TodoTask;

public class AddTodoCommand extends Command {

    private static final String DUKE_ADD_TASK = "Got it. I've added this task:";
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";

    private static final String ERROR_MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task "
            + "cannot be empty.";

    private String description;

    public AddTodoCommand(String description) {
        this.description = description.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeIllegalArgumentException, DukeIOException {
        if (this.description.isEmpty()) {
            throw new DukeIllegalArgumentException(ERROR_MISSING_TASK_DESCRIPTION);
        }

        Task task = new TodoTask(this.description);
        taskList.addTask(task);
        ui.printToUser(DUKE_ADD_TASK,
                       "  " + task.getStatus(),
                       String.format(DUKE_NUMBER_OF_TASKS, taskList.getSize()));
        storage.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
