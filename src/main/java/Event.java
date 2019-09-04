import java.util.ArrayList;

public class Event {
    private String tag = "E";
    private taskDescription;
    private status;
    private time;

    public Event(ArrayList<String> inputList) {
        super(inputList.get(1), inputList.get(2));
        this.taskDescription = super.taskDetails;
        this.status = super.status;
        this.time = super.time;
    }

    @Override

    public String toString(){
        return tag + " | " + status + " | " + taskDescription + " | " + time;
    }


}