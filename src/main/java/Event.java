public class Event extends Task {
    protected String time;

    public Event(String description, String time){
        super(description);
        this.time = time;
    }

    @Override
    public String toFile(){
        return "E | " + (isDone?"1":"0") + " | " +this.description + " | " + this.time + "\n";
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
