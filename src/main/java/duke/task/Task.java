package duke.task;

/**
 * Task class, for inheritance of various tasks.
 */
public class Task {
    private String description;
    private String tag;
    private boolean isDone = false;

    /**
     * Constructs a Task object.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.tag = "";
    }

    /**
     * Returns status icon of the task.
     *
     * @return the tick icon [✓] if task is done, or cross icon [✗] otherwise.
     */
    private String getStatusIcon() {
        //CHECKSTYLE.OFF: AvoidEscapedUnicodeCharactersCheck
        return this.isDone ? "[\u2713]" : "[\u2718]";
        //CHECKSTYLE.ON: AvoidEscapedUnicodeCharactersCheck
    }

    /**
     * Returns the string representation of the tag.
     *
     * @return the string representation of the tag.
     */
    private String getTagString() {
        return this.tag.equals("") ? "" : "[#" + this.tag + "]";
    }

    /**
     * Adds tag to the task.
     */
    public void addTag(String tag) {
        this.tag = tag;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Extracts description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Extracts tag of the task.
     *
     * @return tag of the task.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Gives a string representation of the task for storage.
     *
     * @return a string representation of the task for storage.
     */
    public String toDataString() {
        return (this.isDone ? 1 : 0)
            + " | " + this.description
            + (this.tag.equals("") ? "" : " | " + this.tag);
    }

    @Override
    public String toString() {
        return this.getStatusIcon()
            + this.getTagString()
            + " " + this.description;
    }
}
