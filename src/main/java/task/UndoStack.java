package task;

import exception.DukeUndoException;

import java.util.Stack;
import java.util.ArrayList;

/**
 * A stack which store task list for users to undo their previous actions.
 */
public class UndoStack {

    private static Stack<TaskList> stack;

    private UndoStack() {
        stack = new Stack<TaskList>();
    }

    /**
     * Initialize a UndoStack contructor.
     * Constructor should not be used explicitly.
     */
    public static void initialize() {
        new UndoStack();
    }

    /**
     * Adds a task list into UndoStack.
     * @param tasklist the previous task list.
     */
    public static void add(TaskList tasklist) {
        stack.push(new TaskList(new ArrayList<Task>(tasklist.getList())));
    }

    /**
     * Returns the previous task list.
     * @return previous TaskList.
     * @throws DukeUndoException when there is no action to be undone.
     */
    public static TaskList previousTaskList() throws DukeUndoException {
        if (stack.size() == 0) {
            throw new DukeUndoException();
        } else {
            return stack.pop();
        }
    }


}
