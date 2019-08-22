public class Todo extends Task {

    public Todo(int taskNumber, String taskCheck, String taskName, String type) {
        super(taskNumber, taskCheck, taskName, type);
    }

    public String getAB() {
        return "-";
    }

    @Override
    public String toString() {
        return  Integer.toString(getTaskNumber()) + ".[T]" + getTaskCheck() + " " + getTaskName();
    }
}
