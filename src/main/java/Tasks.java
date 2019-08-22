
public class Tasks {
    String taskDesc;
    String status;
    String tde; 
    String timeOfEvent; 

    public Tasks(String desc, String tde, String time) {
        taskDesc = desc;
        status = "✗";
        if(tde.equals("todo")) {
            tde = "T";
        } else if(tde.equals("deadline")) {
            tde = "D";
        } else {
            tde = "E";
        }
        timeOfEvent = time;
    }

    String getStatus() {
        return status;
    }

    void finishTask() {
        status = "✓";
    }

    @Override 
    public String toString() {
        return "[" + tde + "] " + "[" + status + "] " + taskDesc + timeOfEvent;
    } 

}