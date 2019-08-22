package task.tasks;

import error.EmptyTodoException;
import task.Task;

import java.util.Optional;

public class ToDo extends Task {
    public ToDo(String description) {
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
