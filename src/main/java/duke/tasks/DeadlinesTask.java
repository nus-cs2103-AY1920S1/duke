package duke.tasks;

import java.time.LocalDateTime;
/**
 * Represents a specific type of task - Tasks with deadlines.
 *
 */
public class DeadlinesTask extends Task{
    
    LocalDateTime endingDateTime;

    /**
     * Constructor for DeadlinesTask class.
     *
     * @param status indicates whether task is complete
     * @param message indicates what to do for the task
     * @param endingDateTime deadline of the task
     */
    public DeadlinesTask (Boolean status, String message, LocalDateTime endingDateTime){
        super(status, message, "[D]");
        this.endingDateTime = endingDateTime;
    }

    /**
     * String representation of the DeadlineTask.
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
        String formattedEndingDateTime = endingDateTime.getDayOfMonth() + " " +
                endingDateTime.getMonth() + " " +
                endingDateTime.getHour() +
                endingDateTime.getMinute() +
        " hours" ;

        return  super.type + doneString + " " + super.message + " (by: " +formattedEndingDateTime + ")";
    }

    /**
     * Returns the string representation needed for storage.
     *
     * @return string format needed for storage
     */
    public String toFileFormat(){
        String doneString;
        if(super.status){
            doneString = "1";
            return "D|" +doneString + "|"+ super.message+ "|" + endingDateTime.toString() + "\n";
        }else if (! super.status ){
            doneString = "0";
            return "D|" +doneString + "|"+ super.message+ "|" + endingDateTime.toString() + "\n";
        }else{
            assert false : "Status of a event must be a boolean!" ;  
            return "Status is not a boolean!";         
        }
    }
}