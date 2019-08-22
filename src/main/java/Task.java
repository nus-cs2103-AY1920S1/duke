public class Task {
    private int id;
    private String description;
    public boolean done;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.done = false;
    }

    public int getId() {
        return this.id;
    }
    public String getTask() {
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