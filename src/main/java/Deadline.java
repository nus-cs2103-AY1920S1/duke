public class Deadline extends Task {
    private StringToDate time;

    public Deadline(String name, StringToDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time.toString() + ")";
    }
}