public class Task {

    String taskName;
    boolean isDone;

    Task(String taskName, boolean isDone){
        this.taskName = taskName;
        this.isDone = isDone;
    }

    void setDone(boolean done){
        isDone = done;
    }

    @Override
    public String toString(){
        String mark = isDone ? "✓" : "✗";
        return "[T][" + mark + "] " + taskName;
    }
}
