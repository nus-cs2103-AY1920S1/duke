public class Task{
    boolean done = false;
    String content;
    public Task(String content){
        this.content = content;
    }
    public String toString(){
        String res = "[";
        if(done){
            res += "\u2713";
        }
        else{
            res += "\u2718";
        }
        res += "] " + content;
        return res;
    }
    public void setAsDone(){
        done = true;
    }
}