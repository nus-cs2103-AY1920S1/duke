/**
 * Contains the information and methods of a task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean b){
        this.description = description;
        this.isDone = b;
    }

    /**
     *
     * @return an icon to indicate the status of the task.
     */
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Changes the task status as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     *
     * @return the description of the task along with the status icon.
     */
    @Override
    public String toString(){

        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
