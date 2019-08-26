public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getDate() {
        return "";
    }

    @Override
    public boolean getStatus() {
        return isDone;
    }
}