class Deadline extends Task {
    String date;

    Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        String iconForDone = done ? "v" : "x";
        return String.format("[D][%s] %s (by: %s)", iconForDone, this.task, this.date);
    }
}