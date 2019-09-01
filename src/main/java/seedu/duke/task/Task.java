package seedu.duke.task;

/**
 * Task class represents a Task created by the user.
 * Attribute description holds the Task description.
 * Attribute isDone holds the status of completion of a task.
 * Attribute possibleTaskTypes is an enumerated list of the possible task types ie DEFAULT, DEADLINE, EVENT, TODO.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected possibleTaskTypes taskType;

    /**
     * Returns a Task object after initializing with the String description.
     * By default, isDone is set to false.
     *
     * @param description Description String of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = possibleTaskTypes.DEFAULT;
    }

    /**
     * Returns a Task object after initializing with the String description and isDone status.
     *
     * @param description Description String of the task.
     * @param isDone Boolean status of the task.
     */
    public Task(String description, Boolean isDone){
        this.description = description;
        this.isDone = isDone;
        this.taskType = possibleTaskTypes.DEFAULT;
    }

    /**
     * Returns the Task description String.
     *
     * @return String containing the task description.
     */
    public String getTaskName(){
        return this.description;
    }

    /**
     * Returns a String, which is a tick if isDone is true, or a cross, if isDone is false.
     *
     * @return String representation of isDone.
     */
    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Setter function for setting a Task isDone from false to true.
     */
    public void setDone(){
        if ( !this.isDone ){
            this.isDone = true;
        }
        return;
    }

    /**
     * Returns a parsed String of the Task object, depending on the subclass implementation.
     * Eg. [T][✘] <Task Description>.
     *
     * @return Parsed string.
     */
    public String toString(){
        return ( "[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Returns a parsed String, meant for saving, of the Task object.
     *
     * @return Parsed string for saving.
     */
    public String toSaveString(){
        int booleanNum = (this.isDone ? 1 : 0);

        return ( " | " + booleanNum + " | " + this.description );
    }

    /**
     * Returns the state of a Task, if it is a Default type or not.
     *
     * @return Boolean status if TaskType is default.
     */
    public Boolean isDefault(){
        return this.taskType.equals(possibleTaskTypes.DEFAULT) ;
    }

    /**
     * Returns the state of a Task, if it is a Default type or not.
     *
     * @return Boolean status if TaskType is default.
     */
    public Boolean isTodo(){
        return this.taskType.equals(possibleTaskTypes.TODO) ;
    }

    /**
     * Returns the state of a Task, if it is a Event type or not.
     *
     * @return Boolean status if TaskType is Event.
     */
    public Boolean isEvent(){
        return this.taskType.equals(possibleTaskTypes.EVENT) ;
    }

    /**
     * Returns the state of a Task, if it is a Deadline type or not.
     *
     * @return Boolean status if TaskType is deadline.
     */
    public Boolean isDeadline(){
        return this.taskType.equals(possibleTaskTypes.DEADLINE) ;
    }

    /**
     * An enumeration of the possible task types.
     */
    enum possibleTaskTypes {
        DEFAULT, DEADLINE, EVENT, TODO
    }
}

