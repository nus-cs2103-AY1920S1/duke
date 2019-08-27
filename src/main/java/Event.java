public class Event extends Task {
    protected String at;



    public Event(String description, String at, boolean b){
        super(description);
        this.at = at;
        this.isDone = b;
    }
    public Event(String description, String at){
        super(description);
        this.at = at;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
