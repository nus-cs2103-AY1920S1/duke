class EventTask extends Task {

    private DukeDate time;

    EventTask(String description, DukeDate time) {
        super(description);
        this.time = time;
    }

    EventTask(String description, boolean isDone, DukeDate time) {
        super(description, isDone);
        this.time = time;
    }

    DukeDate getTime() {
        return this.time;
    }

    String getDateAsString() {
        return this.time.format();
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
