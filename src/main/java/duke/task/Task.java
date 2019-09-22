package duke.task;

import duke.exception.DukeException;
import duke.tag.Tag;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Represents a task.
 */
public class Task {
    /** String which represents the description of the task. */
    protected String description;

    /** Boolean which represents whether the task is completed. */
    protected boolean isDone = false;

    /** Hashset of tags belonging to this task. */
    protected HashSet<Tag> tags = new HashSet<Tag>();

    private static final String MESSAGE_DUPLICATE_TAG = "Duplicate tag";

    private static final String MESSAGE_NO_SUCH_TAG = "No such tag";

    /**
     * Initializes a Task Object with the description of the task.
     * Task Object initializes as uncompleted.
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Initializes a Task Object with description and its tags.
     * @param description description of task.
     * @param tags tags belonging to the tasks.
     */
    public Task(String description, HashSet<Tag> tags) {
        this.description = description;
        this.tags = tags;
    }

    /**
     * Returns a status icon to represent whether this task is completed.
     * @return Status icon to represent whether this task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2717");
    }

    /**
     * Completes this task.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks whether the task description contains a certain word.
     * @param word word to search for.
     * @return whether description contains the word.
     */
    public boolean hasWord(String word) {
        return description.toLowerCase().contains(word.toLowerCase());
    }

    /**
     * Returns a string representation to be used for saving data of this object.
     * @return String representation of this object for saving.
     */
    public String getSaveData() {
        String status = isDone ? "1" : "0";
        boolean isTagged = !tags.isEmpty();
        if (isTagged) {
            return status + " \0 " + description + " \0 " + getTags();
        } else {
            return status + " \0 " + description;
        }
    }

    /**
     * Returns string representation of all tags with this task for writing data.
     * @return string representation of tags.
     */
    protected String getTags() {
        StringBuilder sb = new StringBuilder();
        Iterator value = tags.iterator();
        while (value.hasNext()) {
            sb.append(value.next() + " ");
        }
        return sb.toString().trim();
    }

    /**
     * Checks if this task is tagged with the specific tag.
     * @param tag tag to be checked against.
     * @return if task is tagged with the tag.
     */
    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

    /**
     * Adds a tag into its list.
     * @param tag tag to be added.
     * @throws DukeException if its a duplicate tag.
     */
    public void addTag(Tag tag) throws DukeException {
        if (!tags.contains(tag)) {
            tags.add(tag);
        } else {
            throw new DukeException(MESSAGE_DUPLICATE_TAG);
        }
    }

    /**
     * Removes a tag from its list.
     * @param tag tag to be removed.
     * @throws DukeException if there is no such tag.
     */
    public void rmTag(Tag tag) throws DukeException {
        if (tags.contains(tag)) {
            tags.remove(tag);
        } else {
            throw new DukeException(MESSAGE_NO_SUCH_TAG);
        }
    }

    public HashSet<Tag> getTagSet() {
        return tags;
    }

    /**
     * Returns a string representation of this object.
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        boolean isTagged = !tags.isEmpty();
        if (isTagged) {
            return "[" + getStatusIcon() + "] " + description + " " + tags;
        } else {
            return "[" + getStatusIcon() + "] " + description;
        }
    }
}
