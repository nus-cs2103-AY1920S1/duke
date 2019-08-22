
class Tasks {
    String taskDesc;
    String status;

    Tasks(String desc) {
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
    String toString() {
        return "[" + status + "] " + taskDesc;
    } 


}