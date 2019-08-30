package task;

public class ToDo extends Task {
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder("T | ");
        if (isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(description);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[T]");
        if (isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }
        sb.append(description);
        return sb.toString();
    }
}
