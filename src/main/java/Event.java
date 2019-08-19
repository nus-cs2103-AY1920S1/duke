public class Event extends Task {
    private String date;

    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    public String toString() {
        String e = String.format("[E][%s]%s(at:%s)",
                this.getStatusIcon(), this.description, this.date);
        return e;
    }
}
