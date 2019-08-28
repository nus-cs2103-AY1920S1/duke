public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}