public class Event extends Task {
    private String _date;

    /**
     * Creates an Event object, which is also a Task.
     * @param desc a description of the Event Task.
     * @param date the date the Event will be held on.
     */
    public Event(String desc, String date) {
        super(desc);
        this._date = date;
    }

    /**
     * Returns a string representation of a Event object.
     * @return String Returns a string representation of a Event object.
     */
    public String toString() {
        String e = String.format("[E][%s]%s(at:%s)",
                this.getStatusIcon(), this._description, this._date);
        return e;
    }

    /**
     * Returns a string representation of the Event object to be saved
     * into the hard disk file for the Duke program.
     * @return String Returns a the data representation of the Event Task.
     */
    public String toData() {
        String t = String.format("E | %s | %s | %s",
                this.getStatusIcon(), this._description, this._date);
        return t;
    }
}
