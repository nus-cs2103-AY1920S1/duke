public abstract class Task {
    public boolean done;
    public int no;
    public String task;
    public String type;

    public Task(int num, String task, String type) {
        this.no = num;
        this.done = false;
        this.task = task;
        this.type = type;
    }

    public void setDone() {
        this.done = true;
    }

}
