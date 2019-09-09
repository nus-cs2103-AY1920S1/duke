package duke.command;

import duke.exception.DukeIOException;
import duke.exception.DukeIllegalIndexException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.UndoStack;

import duke.task.Task;

/**
 * Represents the "done" command supported by Duke.
 */
public class DoneCommand extends Command implements Undoable {

    private String detail;
    private String finishedIndex;

    public DoneCommand(String detail) {
        this.detail = detail.trim();
    }

    /**
     * Marks a <code>Task</code> in the <code>TaskList</code> as done.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeIllegalIndexException When the index inputted is out of bounds or missing.
     * @throws DukeIOException When there is an error during an input-output process.
     */
    @Override
    public void execute(TaskList taskList, UndoStack undoStack, Ui ui, Storage storage)
            throws DukeIllegalIndexException, DukeIOException {
        try {
            int index = Integer.parseInt(this.detail);
            taskList.markAsDoneTaskAt(index);
            this.finishedIndex = "" + index;
            // Display result to the user
            ui.printToUser(AutoResponse.DUKE_MARK_AS_DONE, "  " + taskList.getTaskAt(index).getStatus());
        } catch (NumberFormatException e) {
            if (this.detail.isEmpty()) {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_MISSING_INDEX);
            }

            if (taskList.isEmpty()) {
                this.finishedIndex = "-1";
                // Display result to the user
                ui.printToUser(AutoResponse.DUKE_NO_TASKS);
            } else if (this.detail.equals("all")) {
                taskList.markAsDoneAllTasks();
                this.finishedIndex = "all";
                String[] lines = taskList.listAll();
                lines[0] = AutoResponse.DUKE_DONE_ALL_TASKS;
                // Display result to the user
                ui.printToUser(lines);
            } else {
                throw new DukeIllegalIndexException(AutoResponse.ERROR_ILLEGAL_INDEX);
            }
        }
        // Add this command to the undoStack
        undoStack.addUndoable(this);
        // Save the updated taskList
        storage.saveTasks(taskList);
    }

    /**
     * Returns false.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void undo(TaskList taskList, Ui ui, Storage storage)
            throws DukeIOException, DukeIllegalIndexException {
        // TODO : java docs
        assert this.finishedIndex != null : "DoneCommand.java (line 83) : finishedIndex should not be null";

        // Display the result to the user
        switch (this.finishedIndex) {
        case "-1":
            ui.printToUser(AutoResponse.DUKE_UNDO_DONE_NO_TASK);
            break;
        case "all":
            taskList.markAsUnfinishedAllTasks();
            ui.printToUser(AutoResponse.DUKE_UNDO_DONE_ALL_TASKS);
            break;
        default:
            int index = Integer.parseInt(this.finishedIndex);
            taskList.markAsUnfinishedTaskAt(index);
            ui.printToUser(AutoResponse.DUKE_UNDO_DONE_TASK,
                    "  " + taskList.getTaskAt(index).getStatus());
            break;
        }
        // Save updated taskList
        storage.saveTasks(taskList);
    }
}
