/**
 * Represents a specific type of task - Tasks that are to-do
 *
 */
class ToDoTask extends Task{
    
    /**
     * Constructor for ToDoTask class
     *
     * @param status indicates whether task is complete
     * @param message indicates what to do for the task
     */
    public ToDoTask (Boolean status, String message){
        super(status, message, "[T]");
    }

    /**
     * String representation of the ToDoTask
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
        return super.type + doneString + " " + super.message;
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
        return "T|" +doneString +"|" + super.message + "\n";
    }
}