package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;
import duke.exception.DukeIllegalIndexException;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

public class DoneCommand extends Command {

    private static final String DUKE_MARK_AS_DONE = "Nice! I've marked this task as done:";
    private static final String DUKE_DONE_ALL_TASKS = "Nice! All of your tasks are marked as done.";

    private static final String ERROR_ILLEGAL_INDEX = "☹ OOPS!!! The index must be a number "
            + "separated by one whitespace.";

    private String detail;

    public DoneCommand(String detail) {
        this.detail = detail;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeIllegalIndexException, DukeIOException {
        try {
            int index = Integer.parseInt(this.detail);
            taskList.markAsDoneTaskAt(index);
            ui.printToUser(DUKE_MARK_AS_DONE, "  " + taskList.getTaskAt(index).getStatus());
        } catch (NumberFormatException e) {
            if (this.detail.equals("all")) {
                taskList.markAsDoneAllTasks();
                String[] lines = taskList.listAll();
                lines[0] = DUKE_DONE_ALL_TASKS;
                ui.printToUser(lines);
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
