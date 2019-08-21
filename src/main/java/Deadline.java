public class Deadline extends Task{
    String by;
    public Deadline(String content, String by){
        super(content);
        this.by = by;
    }
    public String toString(){
        return "[D]"+super.toString()+ " (by: " + by + ")";
    }
}