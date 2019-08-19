public class Deadline extends Task {
    private String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    private String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("  [D][%s]%s(by:%s)", getStatusIcon(), getDescription(), getDate());
    }
}
