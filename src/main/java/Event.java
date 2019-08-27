class Event extends Task {
    private String time;

    /**
     * Creates a new Event with the given description and timing.
     * @param description       Description of Event.
     * @param time              Timing of the Event.
     */
    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns the letter "E", representing the type Event.
     * @return  "E"
     */
    @Override
    String getType() {
        return "E";
    }

    /**
     * Returns a representation of the current Event, including its time, in
     * an appropriate format for data storage.
     * @return  String representing the current Event.
     */
    @Override
    String formatAsData() {
        return super.formatAsData() + " | " + time;
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and time.
     * @return  String describing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
