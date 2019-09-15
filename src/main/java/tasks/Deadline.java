package tasks;

import exceptions.DukeException;
import utils.StringToDate;

public class Deadline extends Task {
    private StringToDate time;

    /**
     *Create a deadline task.
     *
     * @param name the description of the task
     * @param time the time by which to complete it
     */
    public Deadline(String name, StringToDate time) {
        super(name);
        this.time = time;
    }

    /**
     * Mark the task as complete.
     *
     */
    @Override
    public Task markAsDone() throws DukeException {
        Deadline doneTask = new Deadline(this.name, new StringToDate(this.time.getRawDate()));
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
        str += "D" + borderAndSpace;
        if (this.isDone) {
            str += "1" + borderAndSpace;
        } else {
            str += "0" + borderAndSpace;
        }
        str += this.name + borderAndSpace + this.time;
        return str;
    }

    /**
     * Show the user the task.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time.toString() + ")";
    }
}