package duke.tasks;

import java.util.ArrayList;
/**
 * Represents a specific type of task - Tasks that are events
 *
 */
public class Task{

    protected boolean status;
    protected String message;
    protected String type;
    protected ArrayList <String> tagList;

    /**
     * Constructor for EventsTask class
     *
     * @param status indicates whether task is complete
     * @param message indicates what to do for the task
     * @param type indicates the type of the task (e.g. Events/ToDo/Deadlines)
     */
    public Task (Boolean status, String message, String type){
        this.status = status;
        this.message = message;
        this.type = type;
        tagList = new ArrayList<String>();
    }

    /**
     * Sets the status of the task
     * 
     * @param status
     */

    public void setStatus(boolean status){
        this.status = status;
    }

    /**
     * A getter function to get the message of the task
     *  
     * @return the message
     */

    public String getMessage(){
        return message;
    }

    public ArrayList<String> getTagList(){
        return tagList;
    }

    /**
     *Adds a new tag to the arraylist containing all the tags.
     * 
     * @param tag
     */
    public void addTag(String tag){
        tagList.add(tag);
    }

    /**
     * String representation of the Task
     *
     * @return string representation
     */
    @Override
    public String toString() { 
        String doneString;
        if(status){
            doneString = "[✓]";
        }else{
            doneString = "[X]";
        }
        return type + doneString + " " + message;
    }

    /**
     * Returns the string representation needed for storage
     *
     * @return string format needed for storage
     */
    public String toFileFormat(){
        return "dummy";
    }
}