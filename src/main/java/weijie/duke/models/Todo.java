package weijie.duke.models;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDateTimeInfo() {
        return "";
    }

    @Override
    public String getTaskIcon() {
        return "[T]";
    }
}
