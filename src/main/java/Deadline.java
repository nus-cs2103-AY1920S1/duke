public class Deadline extends Task{
    Date date;
    Timing timing = null;
    public Deadline(String description, String date){
        super(description);
        this.date = new Date(date);
        Task.totalTasks++;
    }
    public Deadline(String description, String date, String timing){
        super(description);
        this.date = new Date(date);
        this.timing = new Timing(timing);
        Task.totalTasks++;
    }

//    public String getDateTime(){
//        return date;
//    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + ", " + timing.toString() + ")";
    }
}
