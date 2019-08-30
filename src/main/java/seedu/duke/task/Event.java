package seedu.duke.task;

public class Event extends Task {

    private String location;

    public Event (String description, String location){
        super(description);
        this.location = location;
        taskType = possibleTaskTypes.EVENT;
    }

    public Event(String description, String location, Boolean isDone){
        super(description, isDone);
        this.location = location;
        taskType = possibleTaskTypes.EVENT;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + location + ")";
    }

    @Override
    public String toSaveString(){
        return ( "E" + super.toSaveString() + " | " + this.location);
    }

}
