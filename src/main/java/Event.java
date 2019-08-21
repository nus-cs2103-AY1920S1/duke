public class Event extends Task {
    String _dateTime;

    public Event(String name, String dateTime) {
        super(name);
        _dateTime = dateTime;
    }
    public String toString() {
        String arr[] = _dateTime.split(" ", 2);
        return "[E]" + super.toString() + "(" + arr[0] + ": " + arr[1] + ")";
    }
}
