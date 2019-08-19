public class Task {
    private boolean finished;
    private String task_name;

    public Task(String task_name) {
        this.task_name = task_name;
        this.finished = false;
    }

    public String get_name() {
        return task_name;
    }

    public void set_as_finish() {
        this.finished = true;
    }

    public boolean isFinished() {
        return finished;
    }
}
