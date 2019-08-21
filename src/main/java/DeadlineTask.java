public class DeadlineTask extends Task {

    private String dueDate;

    protected DeadlineTask(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    protected String getStatus() {
        return String.format("[D]%s (by: %s)", super.getStatus(), this.dueDate);
    }

}
