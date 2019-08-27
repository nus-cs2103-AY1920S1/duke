public class Events extends Task {
    private String at;

    public Events(boolean done, String description, String at) {
        super(done, description);
        this.at = at;
    }

    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder("D | ");
        if (isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(description + " | " + at);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[E]");
        if (isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }
        sb.append(description);
        sb.append(" (at: " + at + ")");
        return sb.toString();
    }
}
