package tasks;

public class Task {
    String name;
    boolean isDone;

    /**
     * Create a task object, is called by task, deadline and event.
     *
     * @param name description of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Mark the task as complete.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String printForStorage() {
        return "";
    }

    /**
     * Show the user the task. Called by task, deadline and event classes.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        String str = "";
        if (this.isDone) {
            str += "[✓]";
        } else {
            str += "[✗]";
        } 
        return str += " " + this.name;
    }

}