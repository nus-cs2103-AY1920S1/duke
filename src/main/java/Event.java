public class Event extends Task {
    String timing;
    public Event(String description, String timing){
        super(description);
        this.timing = timing;
        Task.totalTasks++;
    }

    public Event(String description, String timing, int done){
        super(description, done);
        this.timing = timing;
        Task.totalTasks++;
    }

    public String getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timing + ")";
    }
}
