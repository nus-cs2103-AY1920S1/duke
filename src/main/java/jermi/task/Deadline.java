package jermi.task;

public class Deadline extends TaskWithDateTime {

    public Deadline(String description, String deadline) {
        this(description, deadline, "0");
    }

    public Deadline(String description, String deadline, String isDone) {
        super(description, deadline, isDone);
    }

    @Override
    String getTypeCode() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.getDateTime());
    }

}
