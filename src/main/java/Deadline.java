class Deadline extends Task{
    private String deadlineBy;
    Deadline(String taskDetails, String deadlineBy) {
        super(taskDetails);
        this.deadlineBy = deadlineBy;
    }

    String saveInfo() {
        return "deadline" + " " + taskDetails + " /" + deadlineBy + System.getProperty("line.separator")
                + completed;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[D][✓] " + taskDetails + " (" + deadlineBy + ")";
        } else {
            return "[D][✗] " + taskDetails + " (" + deadlineBy + ")";
        }
    }
}
