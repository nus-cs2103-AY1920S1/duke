public class Deadline extends Task {
    protected String date;
    protected String time;

    public Deadline(String desc, String date, String time) {
        super(desc);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        if (time.isEmpty()) {
            return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, date);
        }
        return String.format("[D][%s] %s (by: %s %s)", getStatusIcon(), description, date, time);
    }
}