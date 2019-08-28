/**
 * Represents a specific type of task - Tasks that are events
 *
 */
class EventsTask extends Task{

    DateTime startingDateTime ; 
    DateTime endingDateTime;

    /**
     * Constructor for EventsTask class
     *
     * @param status indicates whether task is complete
     * @param message indicates what to do for the task
     * @param startingDateTime starting date of the task
     * @param endingDateTime ending date of the task
     */
    public EventsTask (Boolean status, String message, 
    DateTime startingDateTime, DateTime endingDateTime){
        super(status, message, "[E]");

        this.startingDateTime = startingDateTime;
        this.endingDateTime = endingDateTime;
    }

    /**
     * String representation of the EventTask
     *
     * @return string representation
     */
    @Override
    public String toString() { 
        String doneString;
        if(super.status){
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        return super.type + doneString + " " + super.message +" (at: " + startingDateTime  + " to " +endingDateTime + ")";
    }

    /**
     * Returns the string representation needed for storage
     *
     * @return string format needed for storage
     */
    public String toFileFormat(){
        String doneString;
        if(super.status){
            doneString = "1";
        }else{
            doneString = "0";
        }
        return "E|" +doneString + "|"+ super.message+ "|" + startingDateTime.toFileString() +" "+ endingDateTime.getHour() + " " + endingDateTime.getMin() + "\n";
    }
}