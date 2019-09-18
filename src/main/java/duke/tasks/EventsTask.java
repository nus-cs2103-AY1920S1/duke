package duke.tasks;

import java.time.LocalDateTime;
/**
 * Represents a specific type of task - Tasks that are events
 *
 */
public class EventsTask extends Task{

    LocalDateTime startingDateTime ; 
    LocalDateTime endingDateTime;

    /**
     * Constructor for EventsTask class
     *
     * @param status indicates whether task is complete
     * @param message indicates what to do for the task
     * @param startingDateTime starting date of the task
     * @param endingDateTime ending date of the task
     */
    public EventsTask (Boolean status, String message, 
    LocalDateTime startingDateTime, LocalDateTime endingDateTime){
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
            doneString = "[\u2713]";
        }else{
            doneString = "[X]";
        }

        String formattedStartingDateTime = startingDateTime.getDayOfMonth() + " " +
                startingDateTime.getMonth() + " " +
                startingDateTime.getHour() +
                startingDateTime.getMinute() +
        " hours" ;

        
        String formattedEndingDateTime = endingDateTime.getDayOfMonth() + " " +
                endingDateTime.getMonth() + " " +
                endingDateTime.getHour() +
                endingDateTime.getMinute() +
        " hours" ;


        return super.type + doneString + " " + super.message +" (at: " + formattedStartingDateTime + " to " + formattedEndingDateTime + ")";
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
            return "E|" +doneString + "|"+ super.message+ "|" + startingDateTime.toString() +"|"+ endingDateTime.toString() + "\n";
        }else if (! super.status){
            doneString = "0";
            return "E|" +doneString + "|"+ super.message+ "|" + startingDateTime.toString() +"|"+ endingDateTime.toString() + "\n";
        }else{
            assert false : "Status of a event must be a boolean!" ;  
            return "Status is not a boolean!";         
        }
    }
}