public class Deadline extends Task {
    protected String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.time);
    }
}
