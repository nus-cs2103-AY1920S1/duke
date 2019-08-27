class DeadlineTask extends Task {

    private String dueDate;

    DeadlineTask(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    String getDueDate() {
        return this.dueDate;
    }

    @Override
    String getStatus() {
        return String.format("[D]%s (by: %s)", super.getStatus(), this.dueDate);
    }

}
