public class Deadline extends Task{
    protected String deadline;
    
    public Deadline(String description){
        super(description.split("/by", 2)[0]);
        this.deadline = description.split("/by", 2)[1];
        this.taskType = "D";
    }
    
    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
        this.taskType = "D";
    }

    public String getDeadline(){
        return this.deadline;
    }
    
    public Task markAsDone(){
        Deadline completed = new Deadline(this.description, this.deadline);
        completed.isDone = true;
        return completed;
    }
    
    public String getTaskStatus(){
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "] " + this.getTaskDescription()
            + "(by:" + this.getDeadline() + ")" ); 
    }
}

