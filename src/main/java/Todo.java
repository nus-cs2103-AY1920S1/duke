public class Todo extends Tasks {
    String tag = "T";
    String taskDescription;
    int status;

    public Todo(ArrayList<String> inputList) {
        this.status = 1;
        this.taskDescription = inputList.get(1);
    }
    public voic finishTask() {
        this.status = 0;
    }

    @Override
    Public String toString() {
        return typeOfTask + " | " + status + " | " + taskDetails;
    }

}