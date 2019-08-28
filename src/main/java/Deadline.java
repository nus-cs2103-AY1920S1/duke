/** Task to represent a deadline. */
class Deadline extends Task {

    protected String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        String doneStr = this.done ? "✓" : "✗";
        return String.format("[D][%s] %s (by: %s)", doneStr, this.name, this.date);
    }
}