public class Deadline extends Task {
    private String time;
    public Deadline(String name, boolean isDone, String time) {
        super(name, isDone);
    }

    public String getTime() {
        return time;
    }
}
