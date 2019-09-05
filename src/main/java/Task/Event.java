package task;

public class Event extends Task {
    private String date;

    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }

}