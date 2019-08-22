
class Tasks {
    String taskDesc;
    String status;

    Tasks(String desc) {
        taskDesc = desc;
        status = 0;
    }

    String getStatus() {
        return status;
    }

    void finishTask() {
        status = 1;
    }

    @Override 
    String toString() {
        return "[" + status + "] " + taskDesc;
    } 


}