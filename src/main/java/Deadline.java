import java.lang.reflect.Array;

public class Deadline extends Task {
    protected Date byDate;
    protected Time byTime;

    public Deadline(String description, String by) {
        super(description);
        String[] temp = by.split(" ");
        byDate = new Date((String) Array.get(temp, 0));
        byTime = new Time((String) Array.get(temp, 1));
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ", " + this.byTime +")";
    }
}
