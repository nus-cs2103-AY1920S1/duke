import java.util.StringJoiner;

public class Event extends Task {

    private String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getSaveString() {
        StringJoiner sj = new StringJoiner("|");
        sj.add("[T]");
        sj.add(isDone ? "1" : "0");
        sj.add(description);
        sj.add(at);
        return sj.toString();
    }

    @Override
    public Event parseSaveString(String saveString) {
        String[] saveStringArr = saveString.split("|");
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