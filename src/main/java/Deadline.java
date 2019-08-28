public class Deadline extends Task {

    Date date;
    Time time;

    public Deadline(int num, String task, Date date, Time time, String type, boolean done) {
        super(num, task, type, done);
        this.date = date;
        this.time = time;
    }

    public Deadline(int num, String task, Date date, Time time, String type) {
        super(num, task, type);
        this.date = date;
        this.time = time;
    }


    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s %s)", done ? "✓" : "✗",task, date, time);
    }

    @Override
    public String fileFormat() { return String.format("D | %s | %s | %s %s", done ? "1" : "0", task, date, time); }

}
