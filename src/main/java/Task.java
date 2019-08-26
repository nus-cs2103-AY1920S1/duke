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

    public String toFile(){
        String mark = isDone ? "1" : "0";
        return "T | " + mark + " |" + taskName;
    }

    @Override
    public String toString(){
        String mark = isDone ? "✓" : "✗";
        return "[T][" + mark + "]" + taskName;
    }
}
