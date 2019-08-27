public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone){
        this.description = desc;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getTask(){
        String output = "[" + getStatusIcon() + "] " + description;
        return output;
    }

    public String writeFormat(){
        return "T " + isDone + " " + description;
    }

    @Override
    public String toString(){
        return "[T]" + getTask();
    }
}
