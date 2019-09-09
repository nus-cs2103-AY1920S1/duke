package duke;

import java.util.ArrayList;

/**
 * Represents a Task object. A <code>Task</code> object corresponds to
 * an agenda to be done.
 */

public class Task {
    String description;
    String type;
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
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Sets done status to true.
     */
    void markDone() {
        this.isDone = true;
    }

    void addTag(String tag) {
        tags.add(tag);
    }

    String getTags() {
        StringBuilder sb = new StringBuilder();
        for (String tag: tags) {
            sb.append("#" + tag);
        }
        return sb.toString();
    }
}
