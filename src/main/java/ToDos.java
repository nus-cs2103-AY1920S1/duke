public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public String format() {
        return "T" + super.format();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
