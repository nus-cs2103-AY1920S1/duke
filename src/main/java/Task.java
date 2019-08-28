/**
 * @author bakwxh
 * @version 0.1
 */
public class Task {
	static String line = "____________________________________________________________";
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     * @param description Description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves status icon.
     * @return Status Icon.
     */
    public String getStatusIcon() {
    	return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    /**
     * Shows task.
     * @return Task in string format.
     */
    public String showTask() {
    	return "[" + getStatusIcon() + "] " + description;
    }
    
    /**
     * Marks task as done.
     */
    public void markAsDone() {
    	isDone = true;
    }
    
    /**
     * Shows task as its saving format.
     * @return Task in string format for saving purposes.
     */
    public String toSave() {
		return null;
	}
}