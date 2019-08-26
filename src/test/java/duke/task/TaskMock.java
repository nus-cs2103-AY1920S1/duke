package duke.task;

public class TaskMock extends Task {
    public TaskMock(String description) {
        super("description");
    }

    @Override
    public String getStatusIcon() {
        return "\u2713"; //return tick or X symbols
    }

    @Override
    public String getDescription(){
        return "description";
    }

}
