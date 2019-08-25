public class Task{
    boolean done = false;
    String content;
    public Task(String content, int status){
        this.content = content;
        done = status == 1 ? true : false;
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
    public String toFile(){
        int d;
        if(done){d = 1;}
        else{d = 0;}
        return d+","+content;
    }
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Task)){
            return false;
        }
        Task t = (Task) o;
        return content.equals(t.content) && done==t.done;
    }
}