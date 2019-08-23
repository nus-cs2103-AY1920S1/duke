public class Task {
    private String description;
    public boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
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
        temp.append(this.description);
        return temp.toString();
    }
}