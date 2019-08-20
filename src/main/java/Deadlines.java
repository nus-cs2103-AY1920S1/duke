public class Deadlines extends Task{
    private String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[D]");
        if(isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }
        sb.append(description);
        sb.append(" (by: " + deadline + ")");
        return sb.toString();
    }
}
