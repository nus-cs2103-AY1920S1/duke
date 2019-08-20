public class Events extends Task{
    private String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[E]");
        if(isDone) {
            sb.append("[✓] ");
        } else {
            sb.append("[✗] ");
        }
        sb.append(description);
        sb.append(" (at: " + at + ")");
        return sb.toString();
    }
}
