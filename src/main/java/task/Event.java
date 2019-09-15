package task;

public class Event extends Task {
    private String date;

    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    public Event(int status, String desc, String date) {
        super(desc);
        this.date = date;
        isDone = (status == 1);
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }

}