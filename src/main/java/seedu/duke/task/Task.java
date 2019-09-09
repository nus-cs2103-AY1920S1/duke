package seedu.duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

    /** Set a task as done and returns an acknowledgement of making the task as done.
     * @return a string to acknowledge successful marking of task as done.
     */
    public String markAsDone() {
        this.isDone = true;
        String reply = "Nice! I've marked this task as done:\n\t" +  " [+] " + description;
        return reply;
    }

    public String getDescription() {
        return description;
    }

    public String writeToFile() {
        return "";
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
