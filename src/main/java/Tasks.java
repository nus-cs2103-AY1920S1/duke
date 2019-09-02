import java.util.ArrayList;

public class Tasks {
    ArrayList<String> taskDescription;
    String typeOfTask;
    String taskDetails;
    String time;
    int status;

    public Tasks(ArrayList<String> inputList) {
        this.taskDescription = inputList;
        if(inputList.get(0).equals("todo")) {
            this.typeOfTask = "T";
            this.taskDetails = inputList.get(1);
            this.time = "";
        } else if(inputList.get(0).equals("deadline")) {
                this.typeOfTask = "D";
                this.taskDetails = inputList.get(1);
                this.time = inputList.get(2);
        } else {
            this.typeOfTask = "E";
            this.taskDetails = inputList.get(1);
            this.time = inputList.get(2);
        }
        this.status = 1;
    }

    protected void finishTask() {
        this.status = 0;
    }

    @Override 
    public String toString() {
        if(this.typeOfTask.equals("T")) {
            return typeOfTask + " | " + status + " | " + taskDetails;
        }  
        return typeOfTask + " | " + status + " | " + taskDetails + " | " + time;
    }

}
