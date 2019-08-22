
public class Tasks {
    String taskDesc;
    String status;
    String tde;  

    public Tasks(String desc, String tde) {
        //taskDesc = desc;
        status = "✗";
        if(tde.equals("todo")) {
            tde = t;
            taskDesc = desc.substring(5);
        } else if(tde.equals("deadline")) {
            tde = d;
            taskDesc = desc.substring(8);
        } else {
            tde = e;
            taskDesc = desc.substring(5);
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