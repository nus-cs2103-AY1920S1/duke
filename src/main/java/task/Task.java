package task;

import tool.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int status;

    /**
     * Constructor for Task
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.status = 0;
    }

    /**
     * Retrieves the status of the task - tick for done, X for not done
     * Default == X
     * @return returns tick or X symbols
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Changes the status of a task from not done to done
     */
    public void markAsDone() {
        this.isDone = true;
        this.status = 1;
    }

    /**
     * Edits the specified attribute of task object with given update.
     * @param attribute: done, des
     * @param update: true/false, new des
     */
    public void edit(String attribute, String update) throws DukeException {
        if (attribute.equals("done")) {
            assert update.equals("true") || update.equals("false") : "Must provide true or false only";
            this.isDone = update.equals("true");
            this.status = update.equals("true") ? 1 : 0;
        } else if (attribute.equals("des")) {
            this.description = update;
        } else {
            throw new DukeException("Attribute does not exist");
        }
    }

    /**
     * Formats the string to how it should be saved in the .txt file
     * @return String to save in the .txt file
     */
    public String storageString() {
        return "task.Task|" + status + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
