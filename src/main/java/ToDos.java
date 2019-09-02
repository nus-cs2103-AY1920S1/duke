public class ToDos extends Task {

    public ToDos(String name , boolean completionStatus ) {
        super(name,completionStatus);
    }

    @Override
    public String getOverallStatus() {
        return "[T]"+ getCurrentStatus() + Description;
    }

    @Override
    public String encodeForStorage(){
        int myInt = isDone ? 1 : 0;
        return "todo [" + myInt + "]" + Description;
    }
}
