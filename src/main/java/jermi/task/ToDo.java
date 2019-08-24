package jermi.task;

public class ToDo extends Task {

    public ToDo(String description) {
        this(description, "0");
    }

    public ToDo(String description, String isDone) {
        super(description, isDone);
    }

    @Override
    String getTypeCode() {
        return "T";
    }
}
