import java.util.StringJoiner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;

    Event(String description, String at) {
        super(description);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.at = LocalDateTime.parse(at, inputFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMMM Y hh:mma");
        return "[E]" + super.toString() + " (at: " + at.format(outputFormat) + ")";
    }

    @Override
    public String getSaveString() {
        StringJoiner sj = new StringJoiner("|");
        sj.add("E");
        sj.add(isDone ? "1" : "0");
        sj.add(description);
        sj.add(at);
        return sj.toString();
    }

    public static Event parseSaveString(String saveString) {
        String[] saveStringArr = saveString.split("\\|");
        boolean isDone = saveStringArr[1].equals("1");
        String description = saveStringArr[2];
        String at = saveStringArr[3];
        Event event = new Event(description, at);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }

}