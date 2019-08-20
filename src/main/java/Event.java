public class Event extends Task {
    private String midcommand; private String date;
    public Event(String command){
        super(command);
        String[]splitteddate = command.split("/");
        date = splitteddate[1];
        midcommand = splitteddate[0];
        this.done = false;
    }
    public String printer(){
        if(done){
            String result = "[E][✓] " + midcommand + "(at: " + date.substring(3) + ")";
            return result;
        }else{
            String result = "[E][✗] " + midcommand + "(at: " + date.substring(3) + ")";
            return result;
        }
    }
    public void taskDone(){
        done = true;
    }
}
