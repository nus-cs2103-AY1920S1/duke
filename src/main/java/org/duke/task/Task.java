package org.duke.task;

import org.duke.DukeException;
import org.duke.json.JsonWriter;
import org.duke.json.Registry;

import java.util.Map;

/**
 * Represents a basic To-Do Task.
 */
public class Task {
    private static final String completedMarker = "[✓]";
    private static final String incompleteMarker = "[✗]";

    static {
        Registry.register(Task.class, Task::serialize);
    }

    private final String description;
    private boolean completed;

    public Task(String description) {
        this(description, false);
    }

    private Task(String description, boolean completed) {
        if (description == null || description.isEmpty()) {
            String message = String.format("The description of a %s cannot be empty.", this.getTaskType());
            throw new DukeException(message);
        }
        this.description = description;
        this.completed = completed;
    }

    Task(Map<String, Object> dict) {
        this((String) dict.get("description"),
                (Boolean) dict.getOrDefault("completed", false));
    }

    public static void serialize(JsonWriter.ValueContext ctx, Task value) {
        ctx.writeObject(value::toJson);
    }


    /**
     * Mark this task as completed.
     */
    public void markComplete() {
        this.completed = true;
    }

    /**
     * Checks if the task is marked as complete.
     *
     * @return Whether task has been marked as completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    public String getDescription() {
        return this.description;
    }

    protected String getTypeMarker() {
        return getTaskType().getMarker();
    }

    protected TaskType getTaskType() {
        return TaskType.ToDo;
    }

    private String getCompleteMarker() {
        return this.completed ? completedMarker : incompleteMarker;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s %s",
                this.getTypeMarker(),
                this.getCompleteMarker(),
                this.description);
    }

    protected void toJson(JsonWriter.ObjectContext ctx) {
        ctx.writeField("type", this.getTaskType());
        ctx.writeField("description", this.description);
        ctx.writeField("completed", this.completed);
    }
}
