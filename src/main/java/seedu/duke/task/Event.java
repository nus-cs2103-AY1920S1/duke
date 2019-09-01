package seedu.duke.task;

/**
 * Event class is a subclass of Task class.
 * Additional attribute is the String location, which holds the venue of the Event task.
 */
public class Event extends Task {

    private String location;

    /**
     * Returns an Event object after initializing with 2 Strings; description and location.
     *
     * @param description Description String of the task.
     * @param location Location String of the task.
     */
    public Event (String description, String location){
        super(description);
        this.location = location;
        taskType = possibleTaskTypes.EVENT;
    }

    /**
     * Returns an Event object after initializing with 2 Strings; description and location;
     * and Boolean status of the task.
     *
     * @param description Description String of the task.
     * @param location Location String of the task.
     * @param isDone isDone status of the task.
     */
    public Event(String description, String location, Boolean isDone){
        super(description, isDone);
        this.location = location;
        taskType = possibleTaskTypes.EVENT;
    }

    /**
     *  Returns a parsed String of the Event object.
     *  Eg. description = "meeting", location = "LT19", isDone = false.
     *  Parsed string = "[E][✘] meeting  (at: LT19)".
     *
     * @return Parsed String of the Event object.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + location + ")";
    }

    /**
     * Returns a parsed String, meant for saving, of the Event object.
     * Eg. description = "meeting", location = "LT19", isDone = false.
     * Parsed saved string = "E | 0 | meeting  | LT19".
     *
     * @return Parsed saved String of the Event object.
     */
    @Override
    public String toSaveString(){
        return ( "E" + super.toSaveString() + " | " + this.location);
    }

}
