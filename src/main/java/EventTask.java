class EventTask extends Task {

    private DukeDate time;

    EventTask(String description, DukeDate time) {
        super(description);
        this.time = time;
    }

    DukeDate getTime() {
        return this.time;
    }

    String getDateAsString() {
        return this.time.format();
    }

    @Override
    String getStatus() {
        return String.format("[E]%s (at: %s)", super.getStatus(), this.time);
    }

}
