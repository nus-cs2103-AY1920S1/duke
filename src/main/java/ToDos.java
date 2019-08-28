public class ToDos extends Task {
    public ToDos(String name , boolean completionStatus ) {
        super(name,completionStatus);
    }

    @Override
    public String getOverallStatus() {
        return "[T]"+ getCurrentStatus() + name;
    }
}
