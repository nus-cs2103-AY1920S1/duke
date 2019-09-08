import java.time.LocalDateTime;
/**
 * Represents a specific type of task - Tasks with deadlines.
 *
 */
class DeadlinesTask extends Task{
    
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
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        String formattedEndingDateTime = String.valueOf(endingDateTime.getDayOfMonth()) + " " +
        String.valueOf(endingDateTime.getMonth()) + " " +
        String.valueOf(endingDateTime.getHour()) + 
        String.valueOf(endingDateTime.getMinute()) +
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
        }else{
            doneString = "0";
        }
        return "D|" +doneString + "|"+ super.message+ "|" + endingDateTime.toString() + "\n";
    }
}