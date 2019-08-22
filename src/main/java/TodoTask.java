class TodoTask  extends Task{
    TodoTask(String taskDetails) {
        super(taskDetails);
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[T][✓] " + taskDetails;
        } else {
            return "[T][✗] " + taskDetails;
        }
    }
}
