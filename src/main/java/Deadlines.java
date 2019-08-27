public class Deadlines extends Task {
    private String deadline;

    public Deadlines(boolean done, String description, String deadline) {
        super(done, description);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder("D | ");
        if (isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(description + " | " + deadline);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[D]");
        if (isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }
        sb.append(description);
        sb.append(" (by: " + deadline + ")");
        return sb.toString();
    }
}
