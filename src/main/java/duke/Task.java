package duke;

import java.util.ArrayList;

/**
 * Represents a Task object. A <code>Task</code> object corresponds to
 * an agenda to be done.
 */

public class Task {
    String description;
    private boolean isDone;
    ArrayList<String> tags = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick symbol if task is done.
     * Otherwise, returns X.
     *
     * @return String tick or X symbol.
     */
    String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    /**
     * Sets done status to true.
     */
    void markDone() {
        this.isDone = true;
    }

    /**
     * Adds a tag to this task.
     */
    void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Returns string containing all tags of this task.
     *
     * @return String containing all tags of this task.
     */
    String getTags() {
        StringBuilder sb = new StringBuilder();
        for (String tag: tags) {
            sb.append("#" + tag);
        }
        return sb.toString();
    }
}
