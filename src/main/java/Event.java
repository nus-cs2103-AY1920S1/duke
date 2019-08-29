public class Event extends Task {
    private String date;

    public Event(String n, String date) {
        super(n);
        this.date = date;
    }

    public Event(String n, String date, boolean completed) {
        super(n, completed);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        String result = "[E][";
        result = this.completed ? result + "\u2713" + "]" : result + "\u2718" + "]";
        result += " " + this.name;
        result += " (at: " + this.date + ")";
        return result;
    }
}
