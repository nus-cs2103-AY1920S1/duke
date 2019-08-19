public class Task {
    private char type ;
    private char status;
    private String taskName;

    public Task() {
        //default constructor
    }

    public Task(String taskName) {
        this.status = '✗';
        this.taskName = taskName;
    }
    public char getType() {
        return type;
    }

    public void setType(char c) {
        type = c;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus() {
        status = '✓';
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
