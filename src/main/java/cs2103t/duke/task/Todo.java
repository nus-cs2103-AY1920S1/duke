package cs2103t.duke.task;

import cs2103t.duke.exception.EmptyDescriptionException;

public class Todo extends Task {

    private Todo() {}
    private Todo(String descr, boolean completed) {
        super.description = descr;
        super.completed = completed;
        super.taskType = TaskType.T;
    }

    public static Todo create(String descr) throws EmptyDescriptionException {
        if (descr.equals(""))
            throw new EmptyDescriptionException("a todo");

        Todo newTask = new Todo(descr.trim(), false);
        return newTask;
    }
}
