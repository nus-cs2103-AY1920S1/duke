public abstract class Task {
    protected boolean isComplete;
    protected String description;

    public Task(String description) {
        this.isComplete = false;
        this.description = description;
    }

    /**
     *  Returns the description of this <code>Task</code>.
     *  @return the description of this <code>Task</code>, as a <code>String</code>.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     *  Returns the completion status of this <code>Task</code>, as a status icon.
     *  @return the single-character <code>String</code> containing the status icon.
     */
    public String getStatusIcon() {
        // Does not return the UTF-16 character for a tick or cross respectively
        // Java SDK 11 incorrectly encodes Unicode characters on Windows returning ? for all of them
        return this.isComplete ? "V" : "X";
    }

    /**
     *  Marks this <code>Task</code> as complete.
     */
    public void complete() {
        this.isComplete = true;
    }

    /**
     *  Returns all information about this <code>Task</code> encoded in a <code>String</code>.
     *  @return a <code>String</code> to be written to a file to persist information of this <code>Task</code>
     */
    public abstract String toEncodedString();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
