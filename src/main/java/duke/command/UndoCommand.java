package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.PastOperationList;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.UndoInfo;

public class UndoCommand extends Command {

    @Override
    public String execute(TaskList taskList, Storage storage, PastOperationList pastOperationList) {
        if (pastOperationList.noPastOperation()) {
            return Messages.NO_COMMAND_TO_UNDO;
        }

        try {
            Task task = pastOperationList.getLastOperation();
            UndoInfo commandsToPerform = pastOperationList.getUndoCommand();
            boolean isSuccessful = false;
            switch (commandsToPerform.getUndoCommand()) {
            case "delete":
                isSuccessful = undoDelete(taskList, task);
                break;
            case "add":
                isSuccessful = undoAdd(taskList, task, commandsToPerform.getIndexToAdd());
                break;
            case "undone":
                isSuccessful = undoDone(task);
                break;
            default:
                isSuccessful = false;
                break;
            }

            if (isSuccessful) {
                return Messages.UNDO_MESSAGE;
            } else {
                return Messages.UNDO_FAILED_MESSAGE;
            }
        } catch (IndexOutOfBoundsException e) {
            return Messages.INVALID_SIZE_EXCEPTION;
        }
    }

    /**
     * Removes the most recently added task.
     *
     * @param taskList is the task list for duke.
     * @param taskToDelete is the recently added task.
     * @return true if duke successfully undo the command, else false.
     */
    public boolean undoDelete(TaskList taskList, Task taskToDelete) {
        return taskList.deleteFromTaskListByTask(taskToDelete);
    }

    /**
     * Adds back the deleted task.
     *
     * @param taskList tasklist of the duke program.
     * @param taskToUndo is the deleted task.
     * @param index is the position where the task was initially at.
     * @return true.
     */
    public boolean undoAdd(TaskList taskList, Task taskToUndo, int index) {
        taskList.addToTaskList(taskToUndo, index);
        return true;
    }

    /**
     * Un-complete the task.
     *
     * @param task is the task to un-complete.
     * @return true.
     */
    public boolean undoDone(Task task) {
        task.setDone(false);
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
