public class Todo extends Task{
    public Todo(String content){
        super(content);
    }
    public String toString(){
        return "[T]"+super.toString();
    }
    public String toFile(){
        return "T,"+super.toFile();
    }
}