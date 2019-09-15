package task;

public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    public ToDo(int status, String desc) {
        super(desc);
        isDone = (status == 1);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
