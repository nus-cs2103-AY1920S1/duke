/**
 * Represents a specific type of task - Tasks with deadlines
 *
 */
class DeadlinesTask extends Task{
    
    DateTime endingDateTime;

    /**
     * Constructor for DeadlinesTask class
     *
     * @param status indicates whether task is complete
     * @param message indicates what to do for the task
     * @param endingDateTime deadline of the task
     */
    public DeadlinesTask (Boolean status, String message, DateTime endingDateTime){
        super(status, message, "[D]");
        this.endingDateTime = endingDateTime;
    }

    /**
     * String representation of the DeadlineTask
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
        return  super.type + doneString + " " + super.message + " (by: " +endingDateTime + ")";
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
        return "D|" +doneString + "|"+ super.message+ "|" + endingDateTime.toFileString() + "\n";
    }
}