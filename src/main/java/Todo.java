public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
        this.taskType = TypeOfTask.TODO;
        this.prefix = "[T]";
        this.details = "";
    }


    @Override
    public String toString() {
        char symbol = this.isCompleted ? '✓' : '✗';
        return prefix + "[" + symbol + "] " + taskName;
    }
}
