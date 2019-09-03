public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean checkDone(){
        return this.isDone;
    }

    protected abstract String toFileString();

    @Override
    public abstract String toString();
}

