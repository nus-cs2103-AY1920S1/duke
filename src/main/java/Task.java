public abstract class Task {

    private boolean finished;
    private String task_name;
    private static int task_numbers = 0;

    Task(String task_name) {
        this.task_name = task_name;
        this.finished = false;
        task_numbers++;
    }

    abstract String task_info();

    abstract String record_info();

    String get_name() {
        return task_name;
    }

    static int get_total_number() {
        return task_numbers;
    }

    static void reduce_total_number() {
        task_numbers--;
    }

    void set_as_finish() {
        this.finished = true;
    }

    boolean isFinished() {
        return finished;
    }
}
