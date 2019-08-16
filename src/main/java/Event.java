public class Event extends TaskWithDate {

    public Event(String line) {
        super(line);
        rebuild(extractDataFromLine(description, " /at "));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}