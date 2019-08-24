public class Todo extends Task {
    public Todo(String description){
        super(description);
        Task.totalTasks++;
    }

    public Todo(String description, int done){
        super(description, done);
        Task.totalTasks++;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
