
public class Deadline extends Task {
    private String date;

    public Deadline(String n, String date) {
        super(n);
        this.date = date;
    }

    public Deadline(String n, String date, boolean completed) {
        super(n, completed);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        String result = "[D][";
        result = this.completed ? result + "✓" + "]" : result + "✘" + "]";
        result += " " + this.name;
        result += " (by: " + this.date + ")";
        return result;
    }
}
