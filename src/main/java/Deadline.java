public class Deadline extends Task {

    String dueDate;

    public Deadline (String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    public Deadline (String task, String dueDate, boolean complete) {
        super(task, complete);
        this.dueDate = dueDate;
    }

    @Override
    public String toStringForFile() {
        String isComplete = this.complete ? "1" : "0";
        return "D | " + isComplete + " | " + task + " | " + dueDate; 
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

}