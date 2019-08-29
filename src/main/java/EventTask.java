class EventTask extends Task {

    private String time;

    EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    EventTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    String getTime() {
        return this.time;
    }

    @Override
    String getType() {
        return "E";
    }

    @Override
    String getStatus() {
        return String.format("[E]%s (at: %s)", super.getStatus(), this.time);
    }

}
