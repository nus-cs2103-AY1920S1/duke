import java.util.StringJoiner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMMM Y hh:mma");

    Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inputFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }

    @Override
    public String getSaveString() {
        StringJoiner sj = new StringJoiner("|");
        sj.add("D");
        sj.add(isDone ? "1" : "0");
        sj.add(description);
        sj.add(by.format(inputFormat));
        return sj.toString();
    }

    public static Deadline parseSaveString(String saveString) {
        String[] saveStringArr = saveString.split("\\|");
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