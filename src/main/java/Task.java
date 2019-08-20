public class Task{
    boolean done = false;
    int order;
    String content;
    public Task(String content, int order){
        this.content = content;
        this.order = order;
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