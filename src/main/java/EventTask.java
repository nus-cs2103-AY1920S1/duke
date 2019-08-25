public class EventTask extends Task {

    String date;

    public EventTask(String details, String date) {
        super(details);
        this.date = date;
    }

    @Override
    protected String toFileString() {
        int done = isDone ? 1 : 0;
        return "E" + " | " + done + " | " + details + " | " + date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}