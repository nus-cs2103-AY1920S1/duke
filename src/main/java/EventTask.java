public class EventTask extends Task {
    private static final String DEFAULT_EVENT_ICON = "[E]";
    private String duration;

    public EventTask(String taskName, String duration) {
        super(taskName, DEFAULT_EVENT_ICON);
        this.duration = duration;
        System.out.println("  " + this);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.duration + ")";
    }
}
