package duke;

public class Todo extends Task {

    public Todo(String description){
        super(description);
        taskType = possibleTaskTypes.TODO;
    }
    public Todo(String description, Boolean isDone){
        super(description, isDone);
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

