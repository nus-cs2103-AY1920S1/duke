public class Task {
    private int id;
    private String task;
    private boolean done;

    public Task(int id, String task) {
        this.id = id;
        this.task = task;
        this.done = false;
    }

    public int getId() {
        return this.id;
    }
    public String getTask() {
        return this.task;
    }
    public boolean isDone() {
        return this.done;
    }
    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        if(this.isDone()) {
            temp.append("[✓] ");
        } else {
            temp.append("[✗] ");
        }
        temp.append(this.task);
        return temp.toString();
    }
}
