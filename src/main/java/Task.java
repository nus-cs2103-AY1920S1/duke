public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     *
     * @param description user input describing task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Set symbol for completion status
     *
     * @return tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }


    /**
     * Mark task as completed
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * returns Task as formatted String
     *
     * @return formatted String
     */
    public String toString() {
        System.out.println(getDescription());
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean equals(Task t) {
        System.out.println(t.getDescription());
        System.out.println(this.getDescription());
        return t.getDescription().equals(this.getDescription());
    }





}
