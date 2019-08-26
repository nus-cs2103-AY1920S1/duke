package duke.Tasks;

import duke.DukeException;

public abstract class Task {

    private boolean finished;
    private String task_name;
    private static int task_numbers = 0;

    public Task(String task_name) throws DukeException {
        if (task_name.equals("")) {
            throw new DukeException("Input task name cannot be empty.");
        }
        this.task_name = task_name;
        this.finished = false;
        task_numbers++;
    }

    public abstract String task_info();

    public abstract String record_info();

    protected String get_name() {
        return task_name;
    }

    public static int get_total_number() {
        return task_numbers;
    }

    public static void reduce_total_number() {
        task_numbers--;
    }

    public void set_as_finish() {
        this.finished = true;
    }

    protected boolean isFinished() {
        return finished;
    }
}
