
public class Tasks {
    String taskDesc;
    String status;
    String tde; 
    //String timeOfEvent; 
    String[] timeOfEvent;

    public Tasks(String desc, String type, String time) {
        taskDesc = desc;
        status = "✗";
        if(type.equals("todo")) {
            tde = "T";
        } else if(type.equals("deadline")) {
            tde = "D";
        } else {
            tde = "E";
        }
        timeOfEvent = time.split(" ");
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