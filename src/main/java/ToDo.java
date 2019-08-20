public class ToDo extends Task {
    //protected Boolean done;
    public ToDo(String command){
        super(command);
        this.done = false;
    }
    public String printer(){
        if(done){
            String result = "[T][✓] " + command;
            return result;
        }else{
            String result = "[T][✗] " + command;
            return result;
        }
    }
    public void taskDone(){
        done = true;
    }
}
