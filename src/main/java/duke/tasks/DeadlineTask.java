package duke.tasks;

public class DeadlineTask extends Task {
    private String date;

    public DeadlineTask(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(at:%s)", super.toString(), date);
    }

    @Override
    public String formatString() {
        return String.format(
                "D|%s|%s", super.formatString(), date);
    }
}