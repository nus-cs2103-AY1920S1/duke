/** Class to represent a task. */
class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    // mark task as done
    public void markDone() {
        this.done = true;
    }

    // get task name
    public String getName() {
        return this.name;
    }
}