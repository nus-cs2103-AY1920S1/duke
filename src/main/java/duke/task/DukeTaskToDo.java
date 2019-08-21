package duke.task;

public class DukeTaskToDo extends DukeTask {
    public DukeTaskToDo(String taskName) {
        super(taskName, "T");
    }

    public DukeTaskToDo(String taskName, boolean isComplete) {
        super(taskName, isComplete, "T");
    }

    @Override
    public String toString() {
        String symbol = getTaskIsComplete() ? "\u2713" : "\u2718";
        return "[" + getTaskType() +"][" + symbol + "] " + getTaskName();
    }
}
