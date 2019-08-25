class Event extends Task {
    String date;

    Event(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        String iconForDone = done ? "v" : "x";
        return String.format("[E][%s] %s (at: %s)", iconForDone, this.task, this.date);
    }
}