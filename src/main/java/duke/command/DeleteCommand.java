package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

public class DeleteCommand extends Command {

    private static final String DUKE_DELETE_TASK = "Noted. I've removed this task:";
    private static final String DUKE_DELETE_ALL_TASKS = "Noted. I've removed all tasks.";
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";

    private static final String ERROR_ILLEGAL_INDEX = "☹ OOPS!!! The index must be a number "
            + "separated by one whitespace.";

    private String detail;

    public DeleteCommand(String detail)  {
        this.detail = detail;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeIllegalIndexException, DukeIOException {
        try {
            int index = Integer.parseInt(this.detail);
            ui.printToUser(DUKE_DELETE_TASK,
                           "  " + taskList.deleteTaskAt(index).getStatus(),
                           String.format(DUKE_NUMBER_OF_TASKS, taskList.getSize()));
        } catch (NumberFormatException e) {
            if (this.detail.equals("all")) {
                taskList.deleteAllTasks();
                ui.printToUser(DUKE_DELETE_ALL_TASKS);
            } else {
                throw new DukeIllegalIndexException(ERROR_ILLEGAL_INDEX);
            }
        }
        storage.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
