public abstract class Task {
    protected String name;
    protected boolean isDone;
    //private Inputs Type;

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public abstract String toString();
}
