public class Deadline extends Task {

    String time;

    public Deadline(int num, String task, String time, String type) {
        super(num, task, type);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", done ? "✓" : "✗",task, time);
    }

}
