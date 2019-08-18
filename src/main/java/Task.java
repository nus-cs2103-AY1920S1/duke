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
        if(isDone){
            return "[" + "\u2713" + "] " + taskName;
        } else {
            return "[" + "\u2718" + "] " + taskName;
        }
    }
}
