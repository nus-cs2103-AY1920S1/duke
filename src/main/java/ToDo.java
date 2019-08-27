public class ToDo extends Task {


    public ToDo(String description, boolean b) {
        super(description);
        this.isDone = b;
    }

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
