class Task {
    boolean done;
    String task;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setDone(boolean status) {
        this.done = status;
    }

    @Override
    public String toString() {
        String iconForDone = done ? "\u2713" : "\u2718";
        return String.format("[%s] %s", iconForDone, this.task);
    }
}