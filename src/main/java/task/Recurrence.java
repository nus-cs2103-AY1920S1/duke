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

    public void setAsRecurring(String unitTime, int quantity){
        isRecurring = true;
        this.unitTime = unitTime;
        this.quantity = quantity;
    }
}
