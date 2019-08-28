public class Deadline extends Task{
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.type = Type.D;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), time);
    }

    @Override
    public String toFile() {
        String doneState = isDone ? "1" : "0";
        return String.format("%s //| %s //| %s //| %s", type, doneState, description, time);
    }
}
