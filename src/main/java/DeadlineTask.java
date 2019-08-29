class DeadlineTask extends Task {

    private DukeDate dueDate;

    DeadlineTask(String description, DukeDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    DeadlineTask(String description, boolean isDone, DukeDate dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    DukeDate getDueDate() {
        return this.dueDate;
    }

    String getDateAsString() {
        return this.dueDate.format();
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
