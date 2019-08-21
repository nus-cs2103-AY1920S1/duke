public class Deadline extends Task {
    String _dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        _dateTime = dateTime;
    }
    public String toString() {
        String arr[] = _dateTime.split(" ", 2);
        return "[D]" + super.toString() + "(" + arr[0] + ": " + arr[1] + ")";
    }
}