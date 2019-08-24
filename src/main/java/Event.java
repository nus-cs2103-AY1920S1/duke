public class Event extends Task {
    Date date;
    Timing timing;
    public Event(String description, String timing){
        super(description);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

    public Event(String description, String date, String timing){
        super(description);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

//    public String getTiming() {
//        return timing;
//    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.toString() + ", " + timing.toString() + ")";
    }
}
