package duke.task;

import java.util.Stack;

/**
 * Keeps track of past modification operations. It will be clear when user closed the program.
 */
public class PastOperationList {
    private Stack<Task> taskPastStack;
    private Stack<UndoInfo> operationToUndo;

    public PastOperationList() {
        taskPastStack = new Stack<>();
        operationToUndo = new Stack<>();
    }

    /**
     * Keeps track of the performed task.
     *
     * @param task is the task that was last executed.
     */
    public void keepTrackOfLastOperation(Task task, UndoInfo undoInfo) {
        if (task != null) {
            taskPastStack.push(task);
            operationToUndo.push(undoInfo);
        }
    }

    /**
     * Retrieves the last performed modification operation executed by the user.
     *
     * @return the last performed modification task runs by the user.
     */
    public Task getLastOperation() {
        return taskPastStack.pop();
    }

    /**
     * Retrieves the command to undo.
     *
     * @return UndoInfo object which consists the command to undo.
     */
    public UndoInfo getUndoCommand() {
        return operationToUndo.pop();
    }

    /**
     * Returns true if there is no operation to undo, else false.
     *
     * @return true if there is no operation to undo, else false.
     */
    public boolean noPastOperation() {
        return taskPastStack.empty();
    }


}
