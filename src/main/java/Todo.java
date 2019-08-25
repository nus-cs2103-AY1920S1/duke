public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
        this.taskType = TypeOfTask.TODO;
        this.details = "";
    }



    public Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.TODO;
        this.details = "";
    }


}
