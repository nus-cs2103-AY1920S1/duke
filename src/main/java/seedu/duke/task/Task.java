package seedu.duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone? "\u2713": "\u2718");
    }

    public String markAsDone(){
        isDone = true;
        String reply = "Nice! I've marked this task as done:\n\t" +  " [\u2713] " + this.description;
        return reply;
    }

    public String toFile(){
        return "";
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
