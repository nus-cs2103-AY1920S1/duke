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
        this.isDone = true;
        String reply = "Nice! I've marked this task as done:\n\t ";
        reply += "  [\u2713] " + this.description;
        return reply;
    }
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
