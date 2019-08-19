public abstract class Task {

    private boolean finished;
    private String task_name;
    private static int task_numbers = 0;

    public Task(String task_name) {
        this.task_name = task_name;
        this.finished = false;
        task_numbers++;
    }

    public abstract String task_info();

    public String get_name() {
        return task_name;
    }

    public static int get_total_number() {
        return task_numbers;
    }

    public void set_as_finish() {
        this.finished = true;
    }

    public boolean isFinished() {
        return finished;
    }
}
