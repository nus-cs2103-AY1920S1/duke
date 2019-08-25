public class Deadline extends Task {
    private String date;

    static Deadline of(String description, String date) throws DukeException {
        if (description.length() == 0 || date.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description or date/time of a deadline cannot be empty.");
        } else {
            return new Deadline(description, date);
        }
    }

    private Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("  [D][%s]%s(by:%s)", getStatusIcon(), getDescription(), getDate());
    }
}
