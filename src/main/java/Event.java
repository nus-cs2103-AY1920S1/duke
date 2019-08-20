public class Event extends Task{
    
    protected String eventDate;
    
    public Event(String description){
        super(description.split("/at", 2)[0]);
        this.eventDate = description.split("/at", 2)[1];
        this.taskType = "E";
    }
    
    public Event(String description, String eventDate){
        super(description);
        this.eventDate = eventDate;
        this.taskType = "E";
    }

    public String getEventDate(){
        return this.eventDate;
    }
    
    public Task markAsDone(){
        Event completed = new Event(this.description, this.eventDate);
        completed.isDone = true;
        return completed;
    }
    
    public String getTaskStatus(){
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "] " + this.getTaskDescription()
            + "(at: " + this.getEventDate() + ")" ); 
    }
}
