import java.util.ArrayList;

public class Deadline extends Tasks {
    private String tag = "D";
    private String taskDescription;
    private String status;
    private time;

    public Deadline(ArrayList<String> inputList) {
        super(inputList.get(1), inputList.get(2));
        this.taskDescription = super.taskDetails;
        this.status = super.status;
        this.time = super.time;
    }
    
    @Override
    public String toString() {
        return tag + " | " + status + " | " + taskDescription + " | " + time;
    }
}