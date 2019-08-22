
class Tasks {
    String taskDesc;
    int status;

    Tasks(String desc) {
        taskDesc = desc;
        status = 0;
    }

    int getStatus() {
        return status;
    }

    void finishTask() {
        status = 1;
    }

    @Override 
    String toString() {
        return taskDesc;
    }


}