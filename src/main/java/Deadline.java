public class Deadline extends TaskWithDate {

    public Deadline(String description, String date) {
        super(description, date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}