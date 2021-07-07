package cs2103t.duke.task;

import cs2103t.duke.exception.EmptyDescriptionException;

/**
 * Represents todo task. Todos have 3 main attributes: their type, description, and
 * whether it is completed.
 */
public class Todo extends Task {
    private Todo() {

    }

    private Todo(String descr, boolean completed) {
        super.description = descr;
        super.isCompleted = completed;
        super.taskType = TaskType.T;
    }

    /**
     * Creates new todo task with the description.
     * @param descr description of todo.
     * @return new todo task.
     * @throws EmptyDescriptionException if description is empty.
     */
    public static Todo create(String descr) throws EmptyDescriptionException {
        if (descr.equals("")) {
            throw new EmptyDescriptionException("a todo");
        }

        Todo newTask = new Todo(descr.trim(), false);
        return newTask;
    }
}
