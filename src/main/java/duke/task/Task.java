package duke.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A prototype object representation of a user task with a description.
 */
public abstract class Task {
    protected static final SimpleDateFormat DATE_WRITER = new SimpleDateFormat("dd/MM/yyyy HHmm");
    protected static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");

    protected boolean isComplete;
    protected String description;
    protected ArrayList<String> tags;

    /**
     * Constructs a <code>Task</code> object with a given description and collection of <code>String</code> tags.
     * 
     * @param description <code>String</code> description of this <code>Task</code>.
     * @param tags an <code>ArrayList</code> of <code>String</code> tags for this task.
     */
    public Task(String description, ArrayList<String> tags) {
        this.isComplete = false;
        this.description = description;
        this.tags = tags;
    }

    /**
     * Returns the description of this <code>Task</code>.
     * 
     * @return the description of this <code>Task</code>, as a <code>String</code>.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Returns the completion status of this <code>Task</code>, as a status icon.
     * 
     * @return the single-character <code>String</code> containing the status icon.
     */
    public String getStatusIcon() {
        // Does not return the UTF-16 character for a tick or cross respectively
        // Java SDK 11 incorrectly encodes Unicode characters on Windows returning ? for all of them
        return this.isComplete ? "V" : "X";
    }

    /**
     * Marks this <code>Task</code> as complete.
     */
    public void complete() {
        this.isComplete = true;
    }

    /**
     * Adds a <code>String</code> tag to this <code>Task</code>.
     * @param tag <code>String</code> tag to apply to this <code>Task</code>.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Returns all information about this <code>Task</code> encoded in a <code>String</code>.
     * 
     * @return a <code>String</code> to be written to a file to persist information of this <code>Task</code>
     */
    public abstract String toEncodedString();

    /**
     * Returns a <code>String</code> built from concatenating the <code>String</code> tags of this <code>Task</code>.
     * 
     * @return a <code>String</code> containing all the tags of this <code>Task</code>.
     */
    protected String tagsToString() {
        return this.tags
            .stream()
            .map(tag -> String.format("#%s", tag))
            .collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        String tagString = this.tags.size() > 0 ? String.format("[%s]", this.tagsToString()) : "";
        return String.format("[%s]%s %s", this.getStatusIcon(), tagString, this.getDescription());
    }
}
