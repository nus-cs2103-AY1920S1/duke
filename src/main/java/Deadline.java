public class Deadline extends TaskWithDateTime {

    Deadline(String description, String deadline) throws EmptyDescriptionException {
        super("a deadline", description, deadline, "0");
    }

    Deadline(String description, String deadline, String isDone) throws EmptyDescriptionException {
        super("a deadline", description, deadline, isDone);
    }

    @Override
    String getTypeCode() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.getDateTime());
    }

}
