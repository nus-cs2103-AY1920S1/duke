package duke;

public abstract class Task {
    public boolean done;
    public int no;
    public String task;
    public String type;

    public Task(int num, String task, String type, boolean done) {
        this.no = num;
        this.done = done;
        this.task = task;
        this.type = type;
    }

    public Task(int num, String task, String type) {
        this.no = num;
        this.done = false;
        this.task = task;
        this.type = type;
    }

    public void setDone() {
        this.done = true;
    }

    public abstract String fileFormat();


}
