public class Task {
    private String command; private Boolean done;
    public Task(String command){
        this.command = command;
        this.done = false;
    }
    public String printer(){
        if(done){
            String result = "[✓] " + command;
            return result;
        }else{
            String result = "[✗]" + command;
            return result;
        }
    }

    public void taskDone(){
        done = true;
    }
}
