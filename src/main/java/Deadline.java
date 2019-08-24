public class Deadline extends Task {
    private String deadline;

    Deadline(String description, String deadline) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("a deadline");
        }
        this.deadline = deadline;
    }

    Deadline(String done, String description, String deadline) {
        super(description);
        if (done.equals("1")) {
            this.markAsDone();
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    String toSaveFormat() {
        return String.format("D|%s|%s", super.toSaveFormat(), this.deadline);
    }
}
