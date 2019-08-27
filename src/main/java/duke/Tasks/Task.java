package duke.Tasks;

import duke.DukeException;

public abstract class Task {

    private boolean finished;
    private String taskName;
    private static int taskNumbers = 0;

    public Task(String task_name) throws DukeException {
        if (task_name.equals("")) {
            throw new DukeException("Input task name cannot be empty.");
        }
        this.taskName = task_name;
        this.finished = false;
        taskNumbers++;
    }

    public abstract String taskInfo();

    public abstract String recordInfo();

    protected String getName() {
        return taskName;
    }

    public boolean match(String s) {
        return taskName.contains(s);
    }

    public static int getTotalNumber() {
        return taskNumbers;
    }

    public static void reduceTotalNumber() {
        taskNumbers--;
    }

    public void setAsFinish() {
        this.finished = true;
    }

    protected boolean isFinished() {
        return finished;
    }
}
