class Task {
    private String description;
    private boolean isDone = false;

    Task(String description) {
        this.description = description;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

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

    String toSaveFormat() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    private String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
