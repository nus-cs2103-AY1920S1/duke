public class Task {
	static String line = "____________________________________________________________";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
    	return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    public String showTask() {
    	return "   [" + getStatusIcon() + "] " + description;
    }
    
    public String showTask(int index) {
    	return " " + index + ".[" + getStatusIcon() + "] " + description;
    }
    
    public void markAsDone() {
    	isDone = true;
    }
}