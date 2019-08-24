class Event extends Task {
    private String date;

    public Event(String task, String date) {
        super(task);
        this.date = date;
    }

    public String toStore() {
        if(super.isCompleted()) {
            return "E | 1 | " + super.toString() + " | " + this.date;
        } else {
            return "E | 0 | " + super.toString() + " | " + this.date;     
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}