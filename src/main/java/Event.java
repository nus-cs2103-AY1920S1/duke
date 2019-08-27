public class Event extends Task {

    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    public Event(String desc, String at, boolean isDone) {
        super(desc, isDone);
        this.at = at;
    }

    @Override
    public String writeFormat(){
        return "E " + isDone + " " + description + "/" + at;
    }

    @Override
    public String toString(){
        return "[E]" + super.getTask() + " (at: " + at + ")";
    }
}
