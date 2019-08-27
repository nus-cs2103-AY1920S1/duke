public class Event extends Task {
    private String date;

    /**
     * Creates an Event object, which is also a Task.
     * @param desc a description of the Event Task.
     * @param date the date the Event will be held on.
     */
    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    /**
     * Returns a string representation of a Event object.
     * @return String Returns a string representation of a Event object.
     */
    public String toString() {
        String e = String.format("[E][%s]%s(at:%s)",
                this.getStatusIcon(), this.description, this.date);
        return e;
    }
}
