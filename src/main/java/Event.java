public class Event extends Task {
    protected String at;

    public Event(String description, int id, String at){
        super(description, id);
        this.at = at;
        this.type = TaskType.EVENT;
    }

    @Override
    public String getDate(){
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}