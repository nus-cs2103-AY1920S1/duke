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
        this.status = 0;
    }

    String getStatus() {
        return status;
    }

    public void finishTask() {
        status = "✓";
    }

   String formatTime() {
        String output = "";
        if(timeOfEvent.length > 1) {
            output = "(" + timeOfEvent[0] + ":";
            for(int i = 1; i < timeOfEvent.length; i++) {
                output = output + " " + timeOfEvent[i];
            }
            output = output + ")";
        }
        return output;
    }
    @Override 
    public String toString() {
        return "[" + tde + "] " + "[" + status + "] " + taskDesc + formatTime();
    } 

}