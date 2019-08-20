public class Deadline extends Task{
    String by;
    public Deadline(String content, int order, String by){
        super(content, order);
        this.by = by;
    }
    public String toString(){
        return "[D]"+super.toString()+ " (by: " + by + ")";
    }
}