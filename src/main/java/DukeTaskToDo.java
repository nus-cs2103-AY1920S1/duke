public class DukeTaskToDo extends DukeTask {
    public DukeTaskToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String symbol = getTaskIsComplete() ? "\u2713" : "\u2718";
        return "[T][" + symbol + "] " + getTaskName();
    }
}
