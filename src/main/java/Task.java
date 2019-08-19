class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor.
     * @param String name
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        String status;
        if (this.isDone) {
            status = "[✓]";
        } else {
            status = "[✗]";
        }
        return String.format("%s %s", status, this.name);
    }
}
