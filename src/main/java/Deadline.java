public class Deadline extends Task {

    String time;

    public Deadline(int num, String task, String time, String type, boolean done) {
        super(num, task, type, done);
        this.time = time;
    }

    public Deadline(int num, String task, String time, String type) {
        super(num, task, type);
        this.time = time;
    }


    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", done ? "✓" : "✗",task, time);
    }

    @Override
    public String fileFormat() { return String.format("D | %s | %s | %s", done ? "1" : "0", task, time); }

}
