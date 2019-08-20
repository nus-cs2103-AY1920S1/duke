public class Deadline extends Task {

    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", "D", super.getDoneSymbol(), this.desc, this.deadline);
    }

}
