package tasklist;

public class Todo extends Task {

    public Todo(String name , boolean completionStatus ) {
        super(name,completionStatus);
    }

    @Override
    public String getOverallStatus() {
        return "[T]"+ getCurrentStatus() + description;
    }

    @Override
    public String encodeForStorage(){
        int myInt = isDone ? 1 : 0;
        return "todo [" + myInt + "]" + description;
    }
}
