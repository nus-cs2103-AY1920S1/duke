public class Todo extends Task{
    public Todo(String content){
        super(content);
    }
    public String toString(){
        return "[T]"+super.toString();
    }
}