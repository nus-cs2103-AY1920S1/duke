public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick or X symbols depending of whether the task is completed.
     * @return tick or X symbols depending of whether the task is completed
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns string representing the completion status as tick or cross the description.
     * @return String representing the completion status as tick or cross the description
     */
    @Override
    public String toString() {
        assert !description.isEmpty() : "Description field is empty";
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns string that representing the completion status as 1 or 0 and the description for saving in txt file.
     * @return String that representing the completion status as 1 or 0 and the description for saving in txt file
     */
    public String toSaveString() {
        assert !description.isEmpty() : "Description field is empty";
        return (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Edits the description of the task.
     * @param newDesc new description to be updated
     */
    public void editDescription(String newDesc) {
        this.description = newDesc;
    }
}

