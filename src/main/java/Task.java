public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean done) {
        this.desc = desc;
        this.isDone = done;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        setDone(true);
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDoneStatus() {
        return isDone() ? "✓" : "✗";
    }

    @Override
    public String toString() {
        return "[" + this.getDoneStatus() + "] " + getDesc();
    }
}
