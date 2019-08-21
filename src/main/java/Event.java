public class Event extends Task{

    protected String eventDate;

    public Event(String sentence) throws DukeException{
        this(sentence.split("/at", 2)[0],(sentence.split("/at", 2).length > 1 ? sentence.split("/at", 2)[1] : ""));
        this.taskType = "E";
    }

    public Event(String description, String eventDate) throws DukeException{
        super(description);
        this.eventDate = eventDate;
        if(this.getTaskDescription().equals("")){
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        if(this.getEventDate().equals("")){
            throw new DukeException("☹ OOPS!!! The date field of a event cannot be empty.");
        }
        this.taskType = "E";
    }

    public String getEventDate(){
        return this.eventDate;
    }

    public Task markAsDone() throws DukeException{
        if(this.isDone){
            throw new DukeException("☹ OOPS!!! The event is already marked as done.");
        }
        Event completed = new Event(this.description, this.eventDate);
        completed.isDone = true;
        return completed;
    }

    public String getTaskStatus(){
        return ("[" + this.getTaskType() + "] " + "[" + this.getStatusIcon() + "]" + this.getTaskDescription()
                + "(at:" + this.getEventDate() + ")" ); 
    }
}
