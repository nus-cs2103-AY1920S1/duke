class DeadlineTask extends Task {

    private String dueDate;

    DeadlineTask(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    DeadlineTask(String description, boolean isDone, String dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    String getDueDate() {
        return this.dueDate;
    }

    @Override
    String getType() {
        return "D";
    }

    @Override
    String getStatus() {
        return String.format("[D]%s (by: %s)", super.getStatus(), this.dueDate);
    }

}
