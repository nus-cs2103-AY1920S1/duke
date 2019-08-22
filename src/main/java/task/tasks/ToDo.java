package task.tasks;

import task.Task;

import java.util.Optional;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description, TaskKeyword.TODO);
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
