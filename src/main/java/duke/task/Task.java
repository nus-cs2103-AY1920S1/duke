package duke.task;

import java.util.ArrayList;

/**
 * Represents a Task.
 * Parent class of all other types of tasks.
 * A <code>Task</code> object corresponds to a task to be added to a <code>TaskList</code>.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructor for <code>Task</code>.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick icon if the task is completed and a cross icon if otherwise.
     * @return Icon to indicate status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns status of the task.
     * @return Whether the task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Adds tags to the task.
     * @param tag Input tag.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Returns the tags in the hashtag form (with a preceding #)
     * @return All tags.
     */
    public String getTagsAsStrings() {
        StringBuilder hashTags = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            hashTags.append("#" + tags.get(i) + " ");
        }
        return hashTags.toString().trim();
    }

    /**
     * Checks the type of the task.
     * @return Type of the task.
     */
    public abstract String getType();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " " + getTagsAsStrings();
    }
}
