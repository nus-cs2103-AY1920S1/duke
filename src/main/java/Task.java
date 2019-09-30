import java.util.LinkedList;

/**
 * Represent Tasks and its given description.
 */
public class Task implements Taggable {
    private String description;
    private boolean isDone;
    private LinkedList<String> allTags;

    /**
     * Creates a new Task with the given description.
     * @param description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.allTags = new LinkedList<>();
    }

    /**
     * Indicates whether the task is done or not.
     * @return true or false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark this task as done and provide notification.
     */
    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:" + "\n" + "  " + this.toString();
    }

    /**
     * Mark this task as done without notification.
     */
    public void quietMarkAsDone() {
        this.isDone = true;
    }

    /**
     * Convert done state of this Task to a symbol.
     * @return + if task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "+" : " "); //return "+" if done " " otherwise
    }

    /**
     * Get the description of this task.
     * @return description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public void addTag(String tag) {
        allTags.add(tag);
    }

    /**
     * Get a string of all tags of this Task.
     * @return String of all tags
     */
    public String getAllTags() {
        String tags = "";
        for (String tag : allTags) {
            tags += tag + " ";
        }
        return tags;
    }

    /**
     * Get a formatted String of all tags if tags are present.
     * @return A formatted string of all tags if tags are present, an empty string otherwise.
     */
    protected String formatTags() {
        if (!allTags.isEmpty()) {
            return (" (tags: " + getAllTags() + ")");
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + this.description);
    }
}