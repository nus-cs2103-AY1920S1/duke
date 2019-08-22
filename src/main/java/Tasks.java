
public class Tasks {
    String taskDesc;
    String status;

    public Tasks(String desc) {
        taskDesc = desc;
        status = "✗";
    }

    String getStatus() {
        return status;
    }

    void finishTask() {
        status = "✓";
    }

    @Override 
    public String toString() {
        return "[" + status + "] " + taskDesc;
    } 


}