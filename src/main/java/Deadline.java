class Deadline extends Task {
    private String date;

    public Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    public String toStore() {
        if(super.isCompleted()) {
            return "D | 1 | " + super.toString() + " | " + this.date;
        } else {
            return "D | 0 | " + super.toString() + " | " + this.date;     
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}