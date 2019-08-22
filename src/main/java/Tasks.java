
public class Tasks {
    String taskDesc;
    String status;
    String tde;  

    public Tasks(String desc, String tde) {
        taskDesc = desc;
        status = "✗";
        if(tde.equals("todo")) {
            tde = t;
        } else if(tde.equals("deadline")) {
            tde = d;
        } else {
            tde = e;
        }
    }

    String getStatus() {
        return status;
    }

    void finishTask() {
        status = "✓";
    }

    @Override 
    public String toString() {
        return "[" + status + "] " + taskDesc;
    } 

}