import java.util.StringJoiner;

public class Deadline extends Task {

    private String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getSaveString() {
        StringJoiner sj = new StringJoiner("|");
        sj.add("[T]");
        sj.add(isDone ? "1" : "0");
        sj.add(description);
        sj.add(by);
        return sj.toString();
    }

    @Override
    public Deadline parseSaveString(String saveString) {
        String[] saveStringArr = saveString.split("|");
        boolean isDone = saveStringArr[1].equals("1");
        String description = saveStringArr[2];
        String by = saveStringArr[3];
        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }
}