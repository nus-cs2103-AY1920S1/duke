package task.tasks;

import error.task.EmptyTodoException;

import java.util.Optional;

/***
 * <p>
 * Basic task.
 * </p>
 */
public class ToDo extends Task {
    /***
     * <p>
     * ToDo constructor.
     * </p>
     * @param description description of task.
     * @throws EmptyTodoException if empty description is given.
     */
    public ToDo(String description) throws EmptyTodoException {
        super(description, TaskKeyword.TODO);
        if (description.equals("")) {
            throw new EmptyTodoException();
        }
    }

    @Override
    protected String getTaskStringCode() {
        return "T";
    }

    @Override
    protected Optional<String> getTaskExtraDetails() {
        return Optional.empty();
    }
}
