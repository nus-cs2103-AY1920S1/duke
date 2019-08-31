package tasks;

import utils.StringToDate;

public class Event extends Task {
    private StringToDate time;

    /**
     * Create an event task.
     *
     * @param name the description of the task
     * @param time the time at which the event takes place
     */
    public Event(String name, StringToDate time) {
        super(name);
        this.time = time;
    }

    /**
     * Print in the correct format for storage and retrieval
     *
     * @return the task in the required format for storage
     */
    @Override
    public String printForStorage() {
        String borderAndSpace = " | ";
        String str = super.printForStorage();
        str += "E" + borderAndSpace;
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
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.toString() + ")";
    }
}