public class ToDos extends Task {

    public ToDos (String description) {
        super(description);
    }

    @Override
    public String formatString() {
        return "T-" + super.checkStatus().trim() + "-" + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
