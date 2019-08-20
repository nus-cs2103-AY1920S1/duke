public class Todo extends Task{
    public Todo(String content, int order){
        super(content, order);
    }
    public String toString(){
        return "[T]"+super.toString();
    }
}