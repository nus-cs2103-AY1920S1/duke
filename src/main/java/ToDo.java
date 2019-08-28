public class ToDo extends Task {

    public ToDo(boolean done, String description) {
        super(description);
        this.isDone = done;
    }

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveFormat() {
        return String.format(" T | %d | %s\n", isDone ? 1 : 0, getDesc());
    }
}

