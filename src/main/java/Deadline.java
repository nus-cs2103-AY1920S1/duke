public class Deadline extends Task {
    private String midcommand; private String date;
    public Deadline(String command){
        super(command);
        this.done = false;
        String[]splitteddate = command.split("/");
        date = splitteddate[1];
        midcommand = splitteddate[0];
    }
    public String printer(){
        if(done){
            String result = "[D][✓] " + midcommand + "(by: " + date.substring(3) + ")";
            return result;
        }else{
            String result = "[D][✗] " + midcommand + "(by: " + date.substring(3) + ")";
            return result;
        }
    }

    public void taskDone(){
        done = true;
    }
}

