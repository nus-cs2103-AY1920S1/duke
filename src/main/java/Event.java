public class Event extends Task {
    private String dateTime;

    Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String[] arr = this.dateTime.split(" ", 2);
        return "[E]" + super.toString() + "(" + arr[0] + ": " + arr[1] + ")";
    }
}
