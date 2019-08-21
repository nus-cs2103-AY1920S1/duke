public class EventTask extends Task {

    private String time;

    protected EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    protected String getStatus() {
        return String.format("[E]%s (at: %s)", super.getStatus(), this.time);
    }

}
