public class Task {
    protected String task_name;
    protected boolean isDone;

    public Task(String task_name) {
        this.task_name = task_name;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : " ");
    }

    public String getType() {
        return "task";
    }

    public String getTypeIcon() {
        return "[]";
    }

    public String getDate() {
        return "";
    }

    @Override
    public String toString() {
        return task_name;
    }
}
