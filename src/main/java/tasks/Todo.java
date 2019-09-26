package tasks;

public class Todo extends Task {

    /**
     * Create a new Todo task.
     * @param name description of the task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Mark the task as complete.
     *
     */
    public Task markAsDone() {
        Todo doneTask = new Todo(this.name);
        doneTask.isDone = true;
        return doneTask;
    }

    /**
     * Print in the correct format for storage and retrieval.
     *
     * @return the task in the required format for storage
     */
    @Override
    public String printForStorage() {
        String borderAndSpace = " | ";
        String str = super.printForStorage();
        str += "T" + borderAndSpace;
        if (this.isDone) {
            str += "1" + borderAndSpace;
        } else {
            str += "0" + borderAndSpace;
        }
        str += this.name + borderAndSpace;
        return str;
    }

    /**
     * Show the user the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}