import java.util.ArrayList;

public class Todo extends Tasks {
    String tag = "T";
    String taskDescription;
    int status;

    public Todo(ArrayList<String> inputList) {
        super(inputList.get(1));
        this.taskDescription = super.taskDetails;
        this.status = super.status;
    }

    @Override
    Public String toString() {
        return tag + " | " + taskDescription + " | " + taskDetails;
    }

}