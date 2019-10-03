package task;

import exception.DukeException;

public abstract class Recurrence extends Task{
    public boolean isRecurring;
    String unitTime;
    int quantity;

    /**
     * Constructor method of Task.
     *
     * @param description contains information of Task. Includes task name and may include date and time.
     */
    public Recurrence(String description) {
        super(description);
    }

    public abstract Task getRecurrence() throws DukeException;

    /**
     * Set task as recurring
     * @param unitTime Recurrence cycle measured in unitTime.
     * @param quantity Amount of unit time per cycle.
     */
    public void setAsRecurring(String unitTime, int quantity){
        isRecurring = true;
        this.unitTime = unitTime;
        this.quantity = quantity;
    }

    /**
     * Reverts task to non recurring.
     */
    public void revert(){
        isRecurring = false;
        this.unitTime = null;
        this.quantity = 0;
    }
}
