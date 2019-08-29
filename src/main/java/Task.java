public class Task {
    public String item;
    public boolean isDone;

    public Task(String item) {
        this.item = item;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public String toDisplay() {
        return this.item;
    }

    public String mark() {
        if (isDone) {
            return ("\u2713");
        } else {
            return ("\u2718");
        }
    }

}
