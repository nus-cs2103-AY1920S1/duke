package task;

import task.tasks.TaskKeyword;

import java.io.Serializable;
import java.util.Optional;

public abstract class Task implements Serializable {
    private String description;
    private boolean done;
    private TaskKeyword keyword;

    public Task(String description, TaskKeyword keyword) {
        this.description = description;
        this.keyword = keyword;
        done = false;
    }

    protected abstract String getTaskStringCode();

    protected abstract Optional<String> getTaskExtraDetails();

    private String getStatusIcon() {
        return done ? "\u2713" : "\u2718";
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public final String getTaskMessage() {
        String message = String.format("[%s][%s] %s",
                getTaskStringCode(),
                getStatusIcon(),
                description);

        return getTaskExtraDetails()
                .map(details -> String.format("%s (%s)", message, details))
                .orElse(message);
    }
}
