public class Todo extends Task {

    public Todo(String description){
        super(description);
        taskType = possibleTaskTypes.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    @Override
    public String toSaveString(){
        return ("T" + super.toSaveString());
    }
}

