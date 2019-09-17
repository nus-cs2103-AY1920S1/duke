public class Task {
    private int index;
    private char type ;
    private char status;
    private String taskName;

    public Task() {
        //default constructor
    }

    public Task(String taskName, int index) {
        this.status = '0';
        this.taskName = taskName;
        this.index = index;
    }
    public char getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public void setType(char c) {
        type = c;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus() {
        status = '1';
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
