package duke;

class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Creates a new task.
     * @param description Task description
     */
    Task(String description) {
        this.description = description;
    }

    /**
     * Creates a new task.
     * @param description Task description
     * @param isDone Flag whether task is done
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Getter for isDone.
     * @return isDone
     */
    boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Getter for description
     * @return Description
     */
    String getDescription() {
        return this.description;
    }

    void markAsDone() {
        this.isDone = true;
    }

    /**
     * Generates the task's textual representation in save file format.
     * @return Textual representation of the task in save file format
     */
    String toSaveFormat() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns a cross or tick icon based on whether the task is done.
     * @return Cross or tick icon
     */
    private String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    /**
     * Generates the task's textual representation in display format.
     * @return Textual representation of the task in display format
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
