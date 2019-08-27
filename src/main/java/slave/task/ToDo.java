package slave.task;

public class ToDo extends Task {

    public ToDo(String description, int id){

        super(description, id);
        this.type = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}