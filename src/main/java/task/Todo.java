package task;

import exception.DukeException;

/**
 * Extends Task. Has a task name.
 */
public class Todo extends Task {

    public boolean isRecurring = false;

    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Set as recurring.
     */
    public void setAsRecurring(){
        isRecurring = true;
    }

    public Task getRecurrence() throws DukeException {
        return new Todo(description);
    }

    /**
     * Reverts task to non recurring.
     */
    public void revert(){
        isRecurring = false;
    }


    @Override
    public String toString() {
        String output = "[T][" + super.getStatusIcon() + "]" + " " + super.description;
        if (isRecurring){
            output += System.lineSeparator() + "recurring";
        }
        return output;
    }

    /**
     * How task data will be stored as a string in the txt file
     * @return task data formatted as String.
     */
    public String parse(){
        String output = "[T][" + super.getStatusIcon() + "]" + " " + super.description;
        if (isRecurring){
            output += " [recurring]";
        }
        return output;
    }
}
