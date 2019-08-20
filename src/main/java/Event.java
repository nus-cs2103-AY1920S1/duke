public class Event extends Task{
    String at;
    public Event(String content, int order, String at){
        super(content, order);
        this.at = at;
    }
    public String toString(){
        return "[E]"+super.toString()+ " (at: " + at + ")";
    }
}